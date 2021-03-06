package com.example.university.repository

import com.example.university.model.*
import com.example.university.network.RetrofitInstance
import retrofit2.Response

class Repository {

    suspend fun getFirstRecyclerModel(): Response<FirstRecyclerModel> {
        return RetrofitInstance.api.getFirstRecyclerModel()
    }

    suspend fun getHeaderRecyclerModel(): ArrayList<Header> {
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

    suspend fun getBachelorPrograms(): ArrayList<BachelorPrograms> {
        val list = arrayListOf<BachelorPrograms>()
        arrayListOf(RetrofitInstance.api.getFirstRecyclerModel()).map {
            list.addAll(it.body()?.bachelorPrograms.orEmpty())
        }
        return list
    }

    suspend fun getSpecialtyPrograms(): ArrayList<SpecialtyPrograms> {
        val list = arrayListOf<SpecialtyPrograms>()
        arrayListOf(RetrofitInstance.api.getFirstRecyclerModel()).map {
            list.addAll(it.body()?.specialtyPrograms.orEmpty())
        }
        return list
    }

    suspend fun getMagistracyPrograms(): ArrayList<MagistracyPrograms> {
        val list = arrayListOf<MagistracyPrograms>()
        arrayListOf(RetrofitInstance.api.getFirstRecyclerModel()).map {
            list.addAll(it.body()?.magistracyPrograms.orEmpty())
        }
        return list
    }

    suspend fun getImageRecyclerModel(): ArrayList<InfoFaculty> {
        val list = arrayListOf<InfoFaculty>()
        arrayListOf(RetrofitInstance.api.getFirstRecyclerModel()).map {
            list.addAll(it.body()?.facultyInformation?.infoFaculty.orEmpty())
        }
        return list
    }

   /* suspend fun getInfoAdmission(): ArrayList<InfoAdmission> {
        val list = arrayListOf<InfoAdmission>()
        arrayListOf(RetrofitInstance.api.getPriemModel()).map {
            list.addAll(it.body()?.infoAdmission.orEmpty())
        }
        return list
    }*/

}