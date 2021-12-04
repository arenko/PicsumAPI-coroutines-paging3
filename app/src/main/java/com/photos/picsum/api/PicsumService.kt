package com.photos.picsum.api

import com.photos.picsum.data.model.PicsumPhoto
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PicsumService {

    @GET("v2/list")
    suspend fun getPhotoList(
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): Response<ArrayList<PicsumPhoto>>


    @GET("id/{id}/info")
    suspend fun getPhoto(@Path("id") id: String): Response<PicsumPhoto>

    @GET("id/{id}/300/500?grayscale")
    suspend fun getGrayscalePhoto(@Path("id") id: String): Response<ResponseBody>

    @GET("id/{id}/300/500?blur")
    suspend fun getBlurPhoto(@Path("id") id: String): Response<ResponseBody>
}