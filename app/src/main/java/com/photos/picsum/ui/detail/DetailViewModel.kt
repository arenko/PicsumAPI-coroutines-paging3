package com.photos.picsum.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.photos.picsum.domain.usecase.PhotoDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val detailUseCase: PhotoDetailUseCase) :
    ViewModel() {

    fun getPhotoDetail(photoId: String) = liveData {
        emit(detailUseCase.getPhotoDetail(photoId))
    }

    fun grayscalePhoto(photoId: String) = liveData {
        emit(detailUseCase.grayscalePhoto(photoId))
    }

    fun blurPhoto(photoId: String) = liveData {
        emit(detailUseCase.blurPhoto(photoId))
    }

}