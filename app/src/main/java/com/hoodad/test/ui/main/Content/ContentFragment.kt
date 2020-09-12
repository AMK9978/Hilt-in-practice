package com.hoodad.test.ui.main.Content

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.hoodad.test.R
import com.hoodad.test.data.models.responses.Episod
import com.hoodad.test.databinding.ContentFragmentLayoutBinding
import com.hoodad.test.ui.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ContentFragment : Fragment() {
    private val contentViewModel: ContentViewModel by viewModels()
    private lateinit var adapter: ContentAdapter
    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var binding: ContentFragmentLayoutBinding

    companion object {
        fun newInstance() = ContentFragment()
    }

    init {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.content_fragment_layout, container, false)
//        binding.root.gotoRegister.setOnClickListener(
//            Navigation.createNavigateOnClickListener(LoginFragmentDirections.actionLoginFragmentToSignUpFragment())
//        )
        setupUI()

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mainViewModel.fetchUsers(45667, false)
        setupObserver()
    }

    private fun setupUI() {
        binding.contentRecycler.layoutManager = LinearLayoutManager(context)
        adapter = ContentAdapter(arrayListOf())
        binding.contentRecycler.addItemDecoration(
            DividerItemDecoration(
                binding.contentRecycler.context,
                (binding.contentRecycler.layoutManager as LinearLayoutManager).orientation
            )
        )
        binding.contentRecycler.adapter = adapter
    }

    private fun setupObserver() {
        mainViewModel.episodes.observe(viewLifecycleOwner, Observer {
            Log.i("TAG", "How is it goin ?!")
            when (it.status) {
                com.hoodad.test.utils.Status.SUCCESS -> {
//                    progressBar.visibility = View.GONE
                    it.data?.let { episodes -> renderList(episodes) }
                    binding.contentRecycler.visibility = View.VISIBLE
                }
                com.hoodad.test.utils.Status.LOADING -> {
//                    progressBar.visibility = View.VISIBLE
                    binding.contentRecycler.visibility = View.GONE
                }
                com.hoodad.test.utils.Status.ERROR -> {
//                    progressBar.visibility = View.GONE
                    Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun renderList(episodes: List<Episod>) {
        adapter.addData(episodes)
        adapter.notifyDataSetChanged()
        Log.i("TAG", "Here's list:" + episodes.size)
    }

}