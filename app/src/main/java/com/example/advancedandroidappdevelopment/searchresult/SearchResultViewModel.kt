package com.example.advancedandroidappdevelopment.main

//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.example.advancedandroidappdevelopment.data.model.ImageSearchResponse
//import com.example.advancedandroidappdevelopment.data.Repository
//import kotlinx.coroutines.launch
//import retrofit2.Response
//
//class SearchResultViewModel(private val repository : Repository) : ViewModel() {
//
//    val myCustomPosts : MutableLiveData<Response<ImageSearchResponse>> = MutableLiveData()
//
//    fun image_search(){
//        viewModelScope.launch {
//            val response = repository.image_search("바다","recency")
//            myCustomPosts.value = response
//        }
//    }
//}