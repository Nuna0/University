package ru.ef.university.network

import retrofit2.Response
import retrofit2.http.GET
import ru.ef.university.model.FirstRecyclerModel

interface MainApi {

    @GET("api.json")
    suspend fun getFirstRecyclerModel(): Response<FirstRecyclerModel>
}