package ru.ef.university.repository

import ru.ef.university.model.*
import ru.ef.university.network.RetrofitInstance
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


}