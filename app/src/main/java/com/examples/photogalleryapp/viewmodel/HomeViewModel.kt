package com.examples.photogalleryapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.examples.photogalleryapp.data.api.ApiInterface
import com.examples.photogalleryapp.data.paging.ImagesPagingSource
import com.examples.photogalleryapp.repository.HomRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class HomeViewModel @Inject constructor ( private val apiInterface: ApiInterface) : ViewModel() {
   private val mutableQuery= MutableLiveData("Fashion")
   private val category= MutableLiveData("cats")

    val images =mutableQuery.switchMap {Pager(config = PagingConfig(
        pageSize = 20,
        maxSize = 100,
        enablePlaceholders = false
    ),
        pagingSourceFactory = { ImagesPagingSource(apiInterface,"","",true) }
    ).liveData.cachedIn(viewModelScope) }

    fun search(query:String){
        mutableQuery.value=query
    }
}