package com.example.advancedandroidappdevelopment.data

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitInstance {
    val apiService: SimpleApi
        get() = instance.create(SimpleApi::class.java)


    private val instance: Retrofit
        private get(){
            val gson = GsonBuilder().setLenient().create()
            return Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }
}
//private val retrofit by lazy {
//    val gson = GsonBuilder().setLenient().create()
//    Retrofit.Builder()
//        .baseUrl(BASE_URL)
//        .addConverterFactory(GsonConverterFactory.create(gson))
//        .build()
//}
//
//val api : SimpleApi by lazy {
//    retrofit.create(SimpleApi::class.java)
//}