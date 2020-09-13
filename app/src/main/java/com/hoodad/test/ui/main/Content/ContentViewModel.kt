package com.hoodad.test.ui.main.Content

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hoodad.test.data.models.DownloadStatus

class ContentViewModel @ViewModelInject constructor() : ViewModel() {
    private var _downloadStatus = MutableLiveData<DownloadStatus>()
    var downloadStatus: LiveData<DownloadStatus> = MutableLiveData(DownloadStatus.IDLE)
        get() = _downloadStatus

    var onComplete: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(ctxt: Context, intent: Intent) {
            _downloadStatus.value = DownloadStatus.Complete
        }
    }

    var onNotificationClick: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(ctxt: Context, intent: Intent) {
            Toast.makeText(ctxt, "Ummmm...hi!", Toast.LENGTH_LONG).show()
        }
    }
}