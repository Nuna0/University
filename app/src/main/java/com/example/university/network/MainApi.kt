package com.example.university.network

import com.example.university.model.FirstRecyclerModel
import com.example.university.model.Priem
import retrofit2.Response
import retrofit2.http.GET

interface MainApi {

    @GET("api.json")
    suspend fun getFirstRecyclerModel(): Response<FirstRecyclerModel>
}