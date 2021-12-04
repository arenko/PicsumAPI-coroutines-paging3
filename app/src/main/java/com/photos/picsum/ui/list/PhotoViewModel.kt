package com.photos.picsum.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.photos.picsum.data.model.PicsumPhoto
import com.photos.picsum.domain.usecase.PhotoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class PhotoViewModel @Inject constructor(private val photoUseCase: PhotoUseCase) : ViewModel() {

    fun getPhotoList(): Flow<PagingData<PicsumPhoto>> {
        return photoUseCase.getPhotoList()
            .map { it }
            .cachedIn(viewModelScope)
    }

}