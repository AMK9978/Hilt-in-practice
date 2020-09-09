package com.hoodad.test.ui.main.Content

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.hoodad.test.R
import com.hoodad.test.databinding.ContentFragmentLayoutBinding
import com.hoodad.test.ui.main.Info.InfoFragment

class ContentFragment : Fragment() {
    private lateinit var loginViewModel: ContentViewModel
    private lateinit var binding: ContentFragmentLayoutBinding

    companion object {
        fun newInstance() = InfoFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        loginViewModel = ViewModelProvider(this).get(ContentViewModel::class.java)
        binding =
            DataBindingUtil.inflate(inflater, R.layout.content_fragment_layout, container, false)
//        binding.root.gotoRegister.setOnClickListener(
//            Navigation.createNavigateOnClickListener(LoginFragmentDirections.actionLoginFragmentToSignUpFragment())
//        )

        return binding.root
    }
}