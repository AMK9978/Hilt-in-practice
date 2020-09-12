package com.hoodad.test.ui.main

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hoodad.test.data.models.responses.BooksInfoResponse
import com.hoodad.test.data.models.responses.Episod
import com.hoodad.test.data.repos.MainRepo
import com.hoodad.test.utils.NetworkHelper
import com.hoodad.test.utils.Resource
import kotlinx.coroutines.launch
import javax.inject.Singleton

@Singleton
class MainViewModel @ViewModelInject constructor
    (
    private val mainRepository: MainRepo,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    private val _booksInfo = MutableLiveData<Resource<BooksInfoResponse>>()
    val booksInfo: LiveData<Resource<BooksInfoResponse>>
        get() = _booksInfo

    private val _episodes = MutableLiveData<Resource<List<Episod>>>()
    val episodes: LiveData<Resource<List<Episod>>>
        get() = _episodes

    fun fetchUsers(boodId: Int, dontGetRelated: Boolean) {
        viewModelScope.launch {
            _booksInfo.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                mainRepository.getBooksInfo(boodId, dontGetRelated).let {
                    if (it.isSuccessful) {
                        _episodes.value = Resource.success(it.body()?.result?.Episodes)
                        _booksInfo.value = Resource.success(it.body())
                        Log.i("TAG", "SIZE:" + it.body()?.result?.Episodes?.size)
                    } else {
                        _episodes.postValue(Resource.error(it.errorBody().toString(), null))
                        _booksInfo.postValue(Resource.error(it.errorBody().toString(), null))
                    }
                }
            } else {
                _booksInfo.postValue(Resource.error(("No Internet connection"), null))
            }
        }
    }
}