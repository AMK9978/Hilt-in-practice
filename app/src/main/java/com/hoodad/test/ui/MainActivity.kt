package com.hoodad.test.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.google.android.material.appbar.AppBarLayout
import com.hoodad.test.R
import com.hoodad.test.databinding.ActivityMainBinding
import com.hoodad.test.ui.main.Comments.CommentsFragment
import com.hoodad.test.ui.main.Content.ContentFragment
import com.hoodad.test.ui.main.Info.InfoFragment
import com.hoodad.test.ui.main.MainViewModel
import com.hoodad.test.ui.main.TabAdapter
import com.hoodad.test.utils.Status
import com.hoodad.test.utils.Util
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.abs


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: TabAdapter
    private lateinit var downloadURL: String
    private val mainViewModel: MainViewModel by viewModels()

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

        binding.appbar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            when {
                abs(verticalOffset) == appBarLayout.totalScrollRange -> {
                    // If collapsed, then do this
                    binding.appBarImage.visibility = View.GONE
                    binding.toolbar.visibility = View.VISIBLE
                }
                verticalOffset == 0 -> {
                    // If expanded, then do this
                    binding.appBarImage.visibility = View.VISIBLE
                    binding.toolbar.visibility = View.GONE
                }
                else -> {
                    // Somewhere in between
                    // Do according to your requirement
                }
            }
        })
        if (this::downloadURL.isInitialized) {
            binding.download.setOnClickListener {
                Util.download(this, downloadURL, 0, "درحال دانلود ")
            }
        }
        init()
        setupObserver()
    }


    private fun setupObserver() {
        mainViewModel.fetchUsers(45667, false)
        mainViewModel.booksInfo.observe(this, Observer {
            Log.i("TAG", "How is it going ?!")
            when (it.status) {
                Status.SUCCESS -> {
                    Glide.with(this).load(it.data?.result?.PhotoUrl).into(binding.bookImage)
                    Glide.with(this).load(it.data?.result?.PhotoUrl).centerCrop()
                        .into(binding.appBarImage)
                    binding.bookTitle.text = it.data?.result?.Title
                    binding.subTitle.text = it.data?.result?.SubTitle
                    binding.priceText.text = it.data?.result?.Price?.PriceDescription
                    binding.rating.text = it.data?.result?.AverageRate.toString()
                    it.data?.result?.SyncUrl?.let {
                        downloadURL = it
                    }
                    Log.i(
                        "TAG",
                        "ARRR:" + it.data?.result?.Title + " " + it.data?.result?.AverageRate.toString()
                    )
                }
                Status.LOADING -> {
                }
                Status.ERROR -> {
                }
            }
        })
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
//        mainViewModel.fetchUsers(45667, false)
    }
}