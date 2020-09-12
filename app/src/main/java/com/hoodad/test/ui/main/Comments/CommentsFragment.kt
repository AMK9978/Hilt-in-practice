package com.hoodad.test.ui.main.Comments

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
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.hoodad.test.R
import com.hoodad.test.data.models.responses.Review
import com.hoodad.test.databinding.CommentsFragmentLayoutBinding
import com.hoodad.test.ui.main.MainViewModel
import com.hoodad.test.utils.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CommentsFragment : Fragment() {
    private lateinit var commentsViewModel: CommentsViewModel
    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var binding: CommentsFragmentLayoutBinding
    private lateinit var adapter: CommentsAdapter

    companion object {
        fun newInstance() = CommentsFragment()
    }

    init {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        commentsViewModel = ViewModelProvider(this).get(CommentsViewModel::class.java)
        binding =
            DataBindingUtil.inflate(inflater, R.layout.comments_fragment_layout, container, false)

        setupUI()
//        binding.root.gotoRegister.setOnClickListener(
//            Navigation.createNavigateOnClickListener(LoginFragmentDirections.actionLoginFragmentToSignUpFragment())
//        )

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mainViewModel.fetchUsers(45667, false)
        setupObserver()
    }

    private fun setupUI() {
        binding.commentsRecycler.layoutManager = LinearLayoutManager(context)
        adapter = CommentsAdapter(arrayListOf())
        binding.commentsRecycler.addItemDecoration(
            DividerItemDecoration(
                binding.commentsRecycler.context,
                (binding.commentsRecycler.layoutManager as LinearLayoutManager).orientation
            )
        )
        binding.commentsRecycler.adapter = adapter
    }

    private fun setupObserver() {
        mainViewModel.booksInfo.observe(viewLifecycleOwner, Observer {
            Log.i("TAG", "How is it goin ?!")
            when (it.status) {
                Status.SUCCESS -> {
//                    progressBar.visibility = View.GONE
                    it.data?.result?.Reviews?.let { episodes -> renderList(episodes) }
                    binding.commentsRecycler.visibility = View.VISIBLE
                }
                Status.LOADING -> {
//                    progressBar.visibility = View.VISIBLE
                    binding.commentsRecycler.visibility = View.GONE
                }
                Status.ERROR -> {
//                    progressBar.visibility = View.GONE
                    Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun renderList(reviews: ArrayList<Review>) {
        adapter.addData(reviews)
        adapter.notifyDataSetChanged()
        Log.i("TAG", "Here's list:" + reviews.size)
    }
}