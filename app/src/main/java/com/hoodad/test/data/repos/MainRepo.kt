package com.hoodad.test.data.repos

import com.hoodad.test.data.api.APIHelper
import javax.inject.Inject

class MainRepo @Inject
constructor(private val apiHelper: APIHelper) {
    suspend fun getBooksInfo(bookId: Int, dontGetRelated: Boolean) =
        apiHelper.getBooksInfo(bookId, dontGetRelated)
}