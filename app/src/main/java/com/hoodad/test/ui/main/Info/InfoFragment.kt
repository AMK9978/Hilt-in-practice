package com.hoodad.test.ui.main.Info

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
import com.hoodad.test.databinding.InfoFragmentLayoutBinding
import com.hoodad.test.ui.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InfoFragment : Fragment() {
    private lateinit var infoViewModel: InfoViewModel
    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var binding: InfoFragmentLayoutBinding

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

        return binding.root
    }

}