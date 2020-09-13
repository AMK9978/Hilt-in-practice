package com.hoodad.test.ui

import android.app.DownloadManager
import android.app.ProgressDialog
import android.content.Context
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions.bitmapTransform
import com.hoodad.test.R
import com.hoodad.test.data.models.Book
import com.hoodad.test.data.models.responses.Genre
import com.hoodad.test.databinding.ActivityMainBinding
import com.hoodad.test.ui.main.Comments.CommentsFragment
import com.hoodad.test.ui.main.Content.ContentFragment
import com.hoodad.test.ui.main.Content.ContentViewModel
import com.hoodad.test.ui.main.Info.GenresAdapter
import com.hoodad.test.ui.main.Info.InfoFragment
import com.hoodad.test.ui.main.MainViewModel
import com.hoodad.test.ui.main.TabAdapter
import com.hoodad.test.utils.Status
import com.hoodad.test.utils.Util
import dagger.hilt.android.AndroidEntryPoint
import jp.wasabeef.glide.transformations.BlurTransformation


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: TabAdapter
    private lateinit var genresAdapter: GenresAdapter
    private lateinit var dialog: ProgressDialog
    private lateinit var downloadURL: String
    private val mainViewModel: MainViewModel by viewModels()
    private val contentViewModel: ContentViewModel by viewModels()

    init {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        adapter = TabAdapter(supportFragmentManager)
        adapter.addFragment(CommentsFragment.newInstance())
        adapter.addFragment(ContentFragment.newInstance())
        adapter.addFragment(InfoFragment.newInstance())
        binding.viewPager.adapter = adapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)
        binding.viewPager.currentItem = 2
        setSupportActionBar(binding.toolbar);
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false);

        binding.download.setOnClickListener {
            if (this::downloadURL.isInitialized) {
                Util.download(
                    this,
                    downloadURL,
                    0,
                    "درحال دانلود ",
                    binding.bookTitle.text.toString()
                )
            }
        }
        setupGenresList()

        init()
        setupObserver()
        registerReceiver(
            contentViewModel.onComplete,
            IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE)
        )
        registerReceiver(
            contentViewModel.onNotificationClick,
            IntentFilter(DownloadManager.ACTION_NOTIFICATION_CLICKED)
        )
    }

    private fun setupGenresList() {
        binding.genresRecycler.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        genresAdapter = GenresAdapter(arrayListOf())
        binding.genresRecycler.addItemDecoration(
            DividerItemDecoration(
                binding.genresRecycler.context,
                (binding.genresRecycler.layoutManager as LinearLayoutManager).orientation
            )
        )
        binding.genresRecycler.adapter = genresAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        if (this::dialog.isInitialized) {
            dialog.dismiss()
        }
        unregisterReceiver(contentViewModel.onComplete);
        unregisterReceiver(contentViewModel.onNotificationClick);
    }

    private fun setupObserver() {
        mainViewModel.fetchUsers(45667, false)
        mainViewModel.booksInfo.observe(this, Observer {
            Log.i("TAG", "How is it going ?!")
            when (it.status) {
                Status.SUCCESS -> {
                    dialog.dismiss()
                    Glide.with(this).load(it.data?.result?.PhotoUrl).into(binding.bookImage)
                    Glide.with(this).load(it.data?.result?.PhotoUrl).centerCrop()
                        .apply(bitmapTransform(BlurTransformation(25, 2)))
                        .into(binding.appBarImage)
                    binding.bookTitle.text = it.data?.result?.Title
                    binding.subTitle.text = it.data?.result?.SubTitle
                    binding.priceText.text = it.data?.result?.Price?.PriceDescription
                    it.data?.result?.Genres?.let { it2 -> renderList(it2) }
                    binding.rating.text = it.data?.result?.AverageRate.toString()
                    it.data?.result?.SyncUrl?.let {
                        downloadURL = it
                    }
                }
                Status.LOADING -> {
                    dialog = ProgressDialog.show(
                        this, "",
                        "لطفا منتظر بمانید", true
                    )
                }
                Status.ERROR -> {
                }
            }
        })
    }

    private fun renderList(genres: List<Genre>) {
        genresAdapter.addData(genres)
        adapter.notifyDataSetChanged()
        Log.i("TAG", "Genres size:" + genres.size)
    }

    private fun init() {
        getSharedPreferences(Util.PREF_NAME, Context.MODE_PRIVATE).edit().putString(
            Util.TOKEN,
            "27A088C8-C424-4E5B-B769-2D9F16991990"
        ).putString(
            Util.APP_VERSION,
            "0"
        ).putString(
            Util.PACKAGE_NAME,
            "1"
        ).putString(
            Util.BUILD_NUMBER,
            "1"
        ).putString(
            Util.DEVICE_ID,
            "1"
        ).putString(
            Util.DEVICE_MODEL,
            "1"
        ).putString(
            Util.DEVICE_TYPE,
            "1"
        ).putString(
            Util.ACCOUNT_TYPE,
            "1"
        ).putString(
            Util.OS_VERSION,
            "0"
        ).putString(
            Util.NEED_PROFILE,
            "true"
        ).commit()
        Log.i(
            "TAG",
            getSharedPreferences(Util.PREF_NAME, Context.MODE_PRIVATE).getString(Util.TOKEN, "")
        )
    }
}