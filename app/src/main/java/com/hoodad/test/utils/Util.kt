package com.hoodad.test.utils

import android.app.DownloadManager
import android.content.Context
import android.database.Cursor
import android.net.Uri

class Util {
    companion object {
        const val PREF_NAME = "APP_PREF"
        const val TOKEN = "PREF_TOKEN"
        const val APP_VERSION = "PREF_APP_VERSION"
        const val PACKAGE_NAME = "PREF_PACKAGE_NAME"
        const val BUILD_NUMBER = "PREF_BUILD_NUMBER"
        const val DEVICE_ID = "PREF_DEVICE_ID"
        const val DEVICE_MODEL = "PREF_DEVICE_MODEL"
        const val DEVICE_TYPE = "PREF_DEVICE_TYPE"
        const val DEVICE_TOKEN = "PREF_DEVICE_TOKEN"
        const val ACCOUNT_TYPE = "PREF_ACCOUNT_TYPE"
        const val REFERRAL = "PREF_REFERRAL"
        const val OS_VERSION = "PREF_OS_VERSION"
        const val USER_ID = "PREF_USER_ID"
        const val NEED_PROFILE = "PREF_NEED_PROFILE"

        fun getPrefString(context: Context, key: String): String {
            return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).getString(key, "")
                ?: ""
        }

        fun download(context: Context, url: String?, totalSize: Long?, title: String) {
            val request = DownloadManager.Request(Uri.parse(url))
            val mgr = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
            request.setTitle(title)
            val id = mgr.enqueue(request)
            val q = DownloadManager.Query()
            q.setFilterById(id)
            val cursor: Cursor = mgr.query(q)
            cursor.moveToFirst()
            val bytes_downloaded: Int =
                cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR))
            cursor.close()
        }
    }

}