package com.hoodad.test.ui.main.Info

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.hoodad.test.R
import com.hoodad.test.data.models.Book
import com.hoodad.test.databinding.InfoFragmentLayoutBinding
import com.hoodad.test.ui.main.MainViewModel
import com.hoodad.test.utils.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InfoFragment : Fragment() {
    private lateinit var infoViewModel: InfoViewModel
    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var adapter: RecommendationAdapter
    private lateinit var binding: InfoFragmentLayoutBinding
    private var showMore: Boolean = false

    companion object {
        fun newInstance() = InfoFragment()
    }

    init {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        infoViewModel = ViewModelProvider(this).get(InfoViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater, R.layout.info_fragment_layout, container, false)

        binding.showMore.setOnClickListener {
            if (showMore) {
                binding.summary.maxLines = 2
                binding.showMore.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.ic_baseline_expand_more_24,
                    0,
                    0,
                    0
                )
                binding.showMore.text = "نمایش بیشتر"
            } else {
                binding.summary.maxLines = Integer.MAX_VALUE
                binding.showMore.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.ic_baseline_expand_less_24,
                    0,
                    0,
                    0
                )
                binding.showMore.text = "نمایش کمتر"
            }
            showMore = !showMore
        }
        setupUI()

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mainViewModel.fetchUsers(45667, false)
        setupObserver()
    }

    private fun setupUI() {
        binding.recommendations.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        adapter = RecommendationAdapter(arrayListOf())
        binding.recommendations.addItemDecoration(
            DividerItemDecoration(
                binding.recommendations.context,
                (binding.recommendations.layoutManager as LinearLayoutManager).orientation
            )
        )
        binding.recommendations.adapter = adapter
    }

    private fun setupObserver() {
        mainViewModel.booksInfo.observe(viewLifecycleOwner, Observer {
            Log.i("TAG", "How is it goin ?!")
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.let {
                        binding.summary.text = it.result?.Content
                        binding.typeTitle.text = it.result?.TypeTitle
                        binding.duration.text = it.result?.BookDurationTitle
                        binding.date.text = it.result?.PersianCreateDate
                        binding.size.text = it.result?.SizeDescription
                        binding.publisherTitle.text = it.result?.PublisherName
                        binding.languageTitle.text = it.result?.Language
                        try {
                            binding.recommendationsTitle.text =
                                it.result?.suggestions?.get(0)?.title
                            it.result?.suggestions?.get(0)?.items?.let { it1 -> renderList(it1) }
                        } catch (exception: IndexOutOfBoundsException) {
                            Log.i("TAG", "Out of bound for recommendations:" + exception.message)
                        }
                    }
                }
                Status.LOADING -> {
                }
                Status.ERROR -> {
                }
            }
        })
    }

    private fun renderList(books: List<Book>) {
        adapter.addData(books)
        adapter.notifyDataSetChanged()
        Log.i("TAG", "Here's list:" + books.size)
    }
}
