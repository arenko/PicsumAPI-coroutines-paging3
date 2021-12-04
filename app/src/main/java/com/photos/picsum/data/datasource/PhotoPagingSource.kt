package com.photos.picsum.data.datasource

import android.accounts.NetworkErrorException
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.photos.picsum.api.PicsumService
import com.photos.picsum.data.model.PicsumPhoto
import javax.inject.Inject

class PhotoPagingSource @Inject constructor(private val picsumService: PicsumService) : PagingSource<Int, PicsumPhoto>() {

    override fun getRefreshKey(state: PagingState<Int, PicsumPhoto>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(PAGE_SIZE) ?: anchorPage?.nextKey?.minus(PAGE_SIZE)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PicsumPhoto> {
        try {
            val nextPageNumber = params.key ?: 1
            val response = picsumService.getPhotoList(nextPageNumber, PAGE_SIZE)

            return if(response.body() != null) {
                var nextKey : Int? = null
                if(response.body()!!.isNotEmpty()){
                    nextKey = nextPageNumber.plus(1)
                }
                LoadResult.Page(
                    data = response.body()!!.toList(),
                    prevKey = null,
                    nextKey = nextKey
                )
            }else{
                LoadResult.Error(NetworkErrorException())
            }
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    companion object {
        const val PAGE_SIZE = 20
    }
}