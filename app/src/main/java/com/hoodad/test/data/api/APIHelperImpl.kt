package com.hoodad.test.data.api

import com.hoodad.test.data.models.responses.BooksInfoResponse
import retrofit2.Response
import javax.inject.Inject

class APIHelperImpl @Inject constructor(private val apiService: APIService) : APIHelper {
    override suspend fun getBooksInfo(
        bookId: Int,
        dontGetRelated: Boolean
    ): Response<BooksInfoResponse> {
        return apiService.getBooksInfo(bookId, dontGetRelated)
    }

}