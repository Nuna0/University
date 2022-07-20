package com.example.university.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.university.model.*
import com.example.university.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel( private val repository: Repository): ViewModel() {

    val myResponse: MutableLiveData<ArrayList<Header>> = MutableLiveData()
    val myFirstResponse: MutableLiveData<Response<FirstRecyclerModel>> = MutableLiveData()
    val myPriemResponse: MutableLiveData<ArrayList<Priem>> = MutableLiveData()
    val myImageSecondResponse: MutableLiveData<ArrayList<InfoFaculty>> = MutableLiveData()
    val myBachelorProgramsResponse: MutableLiveData<ArrayList<BachelorPrograms>> = MutableLiveData()
    val mySpecialtyProgramsResponse: MutableLiveData<ArrayList<SpecialtyPrograms>> = MutableLiveData()
    val myMagistracyProgramsResponse: MutableLiveData<ArrayList<MagistracyPrograms>> = MutableLiveData()


    fun getHeaderRecyclerModel(){
        viewModelScope.launch {
            val response = repository.getHeaderRecyclerModel()
            myResponse.value = response
        }
    }

    fun getFirstRecyclerModel(){
        viewModelScope.launch {
            val response = repository.getFirstRecyclerModel()
            myFirstResponse.value = response
        }
    }

    fun getPriemRecyclerModel(){
        viewModelScope.launch {
            val response = repository.getPriemRecyclerModel()
            myPriemResponse.value = response
        }
    }

    fun getBachelorPrograms(){
        viewModelScope.launch {
            val response = repository.getBachelorPrograms()
            myBachelorProgramsResponse.value = response
        }
    }

    fun getSpecialtyPrograms(){
        viewModelScope.launch {
            val response = repository.getSpecialtyPrograms()
            mySpecialtyProgramsResponse.value = response
        }
    }

    fun getMagistracyPrograms(){
        viewModelScope.launch {
            val response = repository.getMagistracyPrograms()
            myMagistracyProgramsResponse.value = response
        }
    }

    fun getImageSecondModel(){
        viewModelScope.launch {
            val response = repository.getImageRecyclerModel()
            myImageSecondResponse.value = response
        }
    }

}