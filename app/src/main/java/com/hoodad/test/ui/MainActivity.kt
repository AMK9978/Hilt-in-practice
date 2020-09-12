package com.hoodad.test.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
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
import kotlinx.android.synthetic.main.episod_layout.*
import kotlin.math.abs


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: TabAdapter
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
        init()
        setupObserver()
    }


    private fun setupObserver() {
        mainViewModel.episodes.observe(this, Observer {
            Log.i("TAG", "How is it going ?!")
            when (it.status) {
                Status.SUCCESS -> {
                    Log.i("TAG", "what the hell is going on?!")
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
        Log.i("TAG", getSharedPreferences(Util.PREF_NAME, Context.MODE_PRIVATE).getString(Util.TOKEN, ""))
//        mainViewModel.fetchUsers(45667, false)
    }
}