package com.hoodad.test.data.api

import com.google.gson.JsonObject
import com.hoodad.test.data.models.BooksInfoRequestBody
import com.hoodad.test.data.models.responses.BooksInfoResponse
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST

interface APIService {

    @POST("Home/Book")
    fun getBooksInfo(@Body requestBody:RequestBody): Call<BooksInfoResponse>

}