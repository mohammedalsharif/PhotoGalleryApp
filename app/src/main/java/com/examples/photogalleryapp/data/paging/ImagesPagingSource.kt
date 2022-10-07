package com.examples.photogalleryapp.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.examples.photogalleryapp.data.api.ApiInterface
import com.examples.photogalleryapp.data.model.Image
import com.examples.photogalleryapp.data.model.ImageResponse
import okio.IOException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response

private const val PAGE_INDEX = 1

class ImagesPagingSource(
    private val apiInterface: ApiInterface,
    private val query: String,
    private val category: String,
    private val editorChoice: Boolean
) : PagingSource<Int, Image>() {
    override fun getRefreshKey(state: PagingState<Int, Image>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Image> {
        val position = params.key ?: PAGE_INDEX

      return  try {
            val response = apiInterface.getImages(position, query, category, editorChoice)
            val imagesList= response.hits

            LoadResult.Page(data = imagesList, prevKey = if (position == PAGE_INDEX) null else position - 1,
                nextKey = if (imagesList.isEmpty())null else position+1)

        }catch (exceeption:IOException){
            LoadResult.Error(exceeption)
        }catch (exceeption:HttpException){
            LoadResult.Error(exceeption)
        }
    }

}