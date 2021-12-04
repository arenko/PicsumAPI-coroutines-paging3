package com.photos.picsum.domain.usecase

import com.photos.picsum.data.model.PicsumPhoto
import com.photos.picsum.domain.repository.PhotoDetailRepository
import okhttp3.ResponseBody
import retrofit2.Response
import javax.inject.Inject

class PhotoDetailUseCase @Inject constructor(private val photoDetailRepository: PhotoDetailRepository) {
    suspend fun getPhotoDetail(photoId: String): PicsumPhoto? =
        photoDetailRepository.getPhotoDetail(photoId)

    suspend fun grayscalePhoto(photoId: String): ResponseBody? =
        photoDetailRepository.grayscalePhoto(photoId)

    suspend fun blurPhoto(photoId: String): ResponseBody? =
        photoDetailRepository.blurPhoto(photoId)

}