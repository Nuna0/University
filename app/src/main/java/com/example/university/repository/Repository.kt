package com.example.university.repository

import com.example.university.model.Header
import com.example.university.model.Priem
import com.example.university.network.RetrofitInstance

class Repository {
    suspend fun getFirstRecyclerModel(): ArrayList<Header> {
        val list = arrayListOf<Header>()
        arrayListOf(RetrofitInstance.api.getFirstRecyclerModel()).map {
            list.addAll(it.body()?.header.orEmpty())
        }
        return list
    }

    suspend fun getPriemRecyclerModel(): ArrayList<Priem> {
        val list = arrayListOf<Priem>()
        arrayListOf(RetrofitInstance.api.getFirstRecyclerModel()).map {
            list.addAll(it.body()?.priem.orEmpty())
        }
        return list
    }
}