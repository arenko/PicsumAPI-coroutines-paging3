package com.photos.picsum.domain.usecase

import androidx.paging.PagingData
import com.photos.picsum.data.model.PicsumPhoto
import com.photos.picsum.domain.repository.PhotoPagingRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PhotoUseCase @Inject constructor(private val photoPagingRepository: PhotoPagingRepository) {
    fun getPhotoList(): Flow<PagingData<PicsumPhoto>> =
        photoPagingRepository.getPhotoList()
}