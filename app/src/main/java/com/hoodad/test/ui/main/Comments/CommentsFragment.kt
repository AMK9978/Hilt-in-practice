package com.hoodad.test.ui.main.Comments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.hoodad.test.R
import com.hoodad.test.databinding.CommentsFragmentLayoutBinding
import com.hoodad.test.ui.main.Info.InfoFragment

class CommentsFragment : Fragment() {
    private lateinit var commentsViewModel: CommentsViewModel
    private lateinit var binding: CommentsFragmentLayoutBinding

    companion object {
        fun newInstance() = CommentsFragment()
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