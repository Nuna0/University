package com.example.university.network

import com.example.university.model.FirstRecyclerModel
import retrofit2.Response
import retrofit2.http.GET

interface MainApi {

    @GET("raw/mQkJvuAt")
    suspend fun getFirstRecyclerModel(): Response<FirstRecyclerModel>
}