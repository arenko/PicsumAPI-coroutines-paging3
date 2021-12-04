package com.photos.picsum.data.datasource

import com.photos.picsum.api.PicsumService
import com.photos.picsum.data.model.PicsumPhoto
import okhttp3.ResponseBody
import javax.inject.Inject

class PhotoDetailDataSource @Inject constructor(private val picsumService: PicsumService) {

    suspend fun getPhotoDetail(photoId: String): PicsumPhoto? =
        picsumService.getPhoto(photoId).body()

    suspend fun grayscalePhoto(photoId: String): ResponseBody? =
        picsumService.getGrayscalePhoto(photoId).body()

    suspend fun blurPhoto(photoId: String): ResponseBody? =
        picsumService.getBlurPhoto(photoId).body()

}