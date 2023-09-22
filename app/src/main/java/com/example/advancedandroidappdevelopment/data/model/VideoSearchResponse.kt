package com.example.advancedandroidappdevelopment.data.model

import com.google.gson.annotations.SerializedName

data class VideoSearchResponse
    (
    @SerializedName("documents")
            val documents: ArrayList<Documents>,
    @SerializedName("meta")
            val metaData : MetaData
            ){
        data class MetaData(
            @SerializedName("total_count")
            val totalCount : Int,
            @SerializedName("pageable_count")
            val pageableCount : Int,
            @SerializedName("is_end")
            val isEnd : Boolean
        )
        data class Documents(
            @SerializedName("title")
            val title : String,
            @SerializedName("url")
            val url : String,
            @SerializedName("datetime")
            val datetime : String,
            @SerializedName("play_time")
            val play_time : Int,
            @SerializedName("thumbnail")
            val thumbnail : String,
            @SerializedName("author")
            val author : String
        )


    }

