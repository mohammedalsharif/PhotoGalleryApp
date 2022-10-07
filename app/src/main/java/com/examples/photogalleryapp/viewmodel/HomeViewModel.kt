package com.examples.photogalleryapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.examples.photogalleryapp.repository.HomRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
class HomeViewModel  constructor (private val repository: HomRepository) : ViewModel() {
   private val mutableQuery= MutableLiveData("Fashion")
   private val category= MutableLiveData("cats")

    val images =mutableQuery.switchMap { mutableQuery->repository.getSearchResult(mutableQuery,"",true).cachedIn(viewModelScope) }

    fun search(query:String){
        mutableQuery.value=query
    }
}