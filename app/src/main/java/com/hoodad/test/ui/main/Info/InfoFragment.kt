package com.hoodad.test.ui.main.Info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.hoodad.test.R
import com.hoodad.test.databinding.InfoFragmentLayoutBinding

class InfoFragment : Fragment() {
    private lateinit var loginViewModel: InfoViewModel
    private lateinit var binding: InfoFragmentLayoutBinding

    companion object {
        fun newInstance() = InfoFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        loginViewModel = ViewModelProvider(this).get(InfoViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater, R.layout.info_fragment_layout, container, false)

        return binding.root
    }

}