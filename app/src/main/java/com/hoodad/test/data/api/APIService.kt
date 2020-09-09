package com.hoodad.test.data.api

import com.hoodad.test.data.models.responses.BooksInfoResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface APIService {

    @POST("Home/Book")
    @FormUrlEncoded
    fun getBooksInfo(
        @Field("BookId") bookId: Int, @Field("DontGetRelated") dontGetRelated: Boolean
    ): Response<BooksInfoResponse>

}