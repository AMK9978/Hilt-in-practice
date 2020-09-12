package com.hoodad.test.data.api


import com.hoodad.test.data.models.responses.BooksInfoResponse
import retrofit2.Call
import retrofit2.Response

interface APIHelper {
    companion object {

    }
    suspend fun getBooksInfo(bookId: Int, dontGetRelated:Boolean): Response<BooksInfoResponse>
}