package com.photos.picsum.data.model

import com.google.gson.annotations.Expose

data class PicsumPhoto(
    @Expose
    val id: String,
    @Expose
    val author: String,
    @Expose
    val download_url: String
)