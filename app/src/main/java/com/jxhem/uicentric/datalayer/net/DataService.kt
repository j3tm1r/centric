package com.jxhem.uicentric.datalayer.net

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface DataService {

    @GET("comments")
    fun getComments(): Call<List<Model.Comment>>

    @GET("comments/{comment}")
    fun getComment(@Path(value = "comment") commentId: Long): Call<Model.Comment>


    companion object {
        val BASE_URL = "https://jsonplaceholder.typicode.com/"
    }
}