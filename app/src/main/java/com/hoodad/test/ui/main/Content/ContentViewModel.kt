package com.hoodad.test.ui.main.Content

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hoodad.test.data.models.responses.Episod
import com.hoodad.test.utils.Resource

class ContentViewModel @ViewModelInject constructor() : ViewModel() {
    private val _episodes = MutableLiveData<Resource<List<Episod>>>()
    val episodes: LiveData<Resource<List<Episod>>>
        get() = _episodes
}