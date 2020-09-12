package com.hoodad.test.ui.main.Comments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.hoodad.test.R
import com.hoodad.test.databinding.CommentsFragmentLayoutBinding
import com.hoodad.test.ui.main.Info.InfoFragment
import com.hoodad.test.ui.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CommentsFragment : Fragment() {
    private lateinit var commentsViewModel: CommentsViewModel
    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var binding: CommentsFragmentLayoutBinding

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
//        binding.root.gotoRegister.setOnClickListener(
//            Navigation.createNavigateOnClickListener(LoginFragmentDirections.actionLoginFragmentToSignUpFragment())
//        )

        return binding.root
    }
}