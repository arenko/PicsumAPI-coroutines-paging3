package com.photos.picsum.domain.repository

import com.photos.picsum.data.datasource.PhotoDetailDataSource
import com.photos.picsum.data.model.PicsumPhoto
import okhttp3.ResponseBody
import javax.inject.Inject

class PhotoDetailRepository @Inject constructor(private val detailDataSource: PhotoDetailDataSource) {

    suspend fun getPhotoDetail(photoId: String): PicsumPhoto? =
        detailDataSource.getPhotoDetail(photoId)

    suspend fun grayscalePhoto(photoId: String): ResponseBody? =
        detailDataSource.grayscalePhoto(photoId)

    suspend fun blurPhoto(photoId: String): ResponseBody? =
        detailDataSource.blurPhoto(photoId)
}