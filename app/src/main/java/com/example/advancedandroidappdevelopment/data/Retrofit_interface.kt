package com.example.advancedandroidappdevelopment.data

import com.example.advancedandroidappdevelopment.data.model.ImageSearchResponse
import com.example.advancedandroidappdevelopment.data.model.VideoSearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface Retrofit_interface {
    // 존재하지 않는 페이지를 호출하는 등의 통신 오류가 발생할 경우 충돌이 발생하게 된다.
    @GET("v2/search/image")
    fun image_search(
        @Header("Authorization") apiKey: String ,
        @Query("query") query : String,
        @Query("sort") sort : String,
        @Query("page") page: Int,
        @Query("size") size: Int
    ) : Call<ImageSearchResponse?>?

    @GET("v2/search/vclip")
    fun vedio_search(
        @Header("Authorization") apiKey: String ,
        @Query("query") query : String,
        @Query("sort") sort : String,
        @Query("page") page: Int,
        @Query("size") size: Int
    ) : Call<VideoSearchResponse?>?
}


//@GET("v2/search/image")
//suspend fun image_search(
//    @Header("Authorization") apiKey: String ,
//    @Query("query") query : String,
//    @Query("sort") sort : String,
//    @Query("page") page: Int,
//    @Query("size") size: Int
//) : Response<ImageSearchResponse>

//Call은 비동기 요청을 나타내며 콜백을 사용하여 응답 처리를 기다립니다.
//Response는 동기 요청에서 사용되며, 요청을 실행하고 즉시 응답을 반환합니다.
//Response 객체는 응답 본문을 직접 추출하고 HTTP 응답 코드를 확인하는 데 사용됩니다.
//Call을 사용하면 비동기적으로 요청을 보내고 콜백을 통해 응답을 처리할 수 있으므로
//UI 스레드를 차단하지 않습니다. 반면 Response는 동기적으로 작동하므로 주로 백그라운드 스레드에서 사용됩니다.