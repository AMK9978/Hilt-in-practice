package com.hoodad.test.data.api

import android.util.Log
import com.google.gson.JsonObject
import com.hoodad.test.data.models.BooksInfoRequestBody
import com.hoodad.test.data.models.responses.BooksInfoResponse
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.awaitResponse
import java.lang.Exception
import javax.inject.Inject


class APIHelperImpl @Inject constructor(private val apiService: APIService) : APIHelper {
    override suspend fun getBooksInfo(
        bookId: Int,
        dontGetRelated: Boolean
    ): Response<BooksInfoResponse> {
        Log.i("TAG", "bookID:$bookId $dontGetRelated")
        val jsonObject = JsonObject()
        jsonObject.addProperty("BoodId", bookId)
        jsonObject.addProperty("DontGetRelated", dontGetRelated)
        val req = BooksInfoRequestBody(bookId, dontGetRelated)
        val req2 = RequestBody.create(MediaType.parse("application/json"), "{\r\n  \"BookId\" : 45667,\r\n  \"DontGetRelated\": false\r\n}")
        try {
            return apiService.getBooksInfo(req2).awaitResponse()
        }catch (exc: Exception){
            Log.i("TAG", "EE:" + exc.message)
        }
        return Response.success(null)
    }

}