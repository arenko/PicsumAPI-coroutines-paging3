package com.photos.picsum.domain.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.photos.picsum.api.PicsumService
import com.photos.picsum.data.datasource.PhotoPagingSource
import com.photos.picsum.data.model.PicsumPhoto
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PhotoPagingRepository @Inject constructor(private val picsumService: PicsumService) {

    fun getPhotoList(): Flow<PagingData<PicsumPhoto>> {
        return Pager(
            config = getDefaultPageConfig(),
            pagingSourceFactory = { PhotoPagingSource(picsumService) }
        ).flow
    }

    fun getDefaultPageConfig(): PagingConfig {
        return PagingConfig(pageSize = PhotoPagingSource.PAGE_SIZE, enablePlaceholders = false)
    }

}