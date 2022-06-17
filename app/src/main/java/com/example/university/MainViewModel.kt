package com.example.university

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.university.model.Header
import com.example.university.model.Priem
import com.example.university.repository.Repository
import kotlinx.coroutines.launch

class MainViewModel( private val repository: Repository): ViewModel() {

    val myResponse: MutableLiveData<ArrayList<Header>> = MutableLiveData()
    val myPriemResponse: MutableLiveData<ArrayList<Priem>> = MutableLiveData()

    fun getFirstRecyclerModel(){
        viewModelScope.launch {
            val response = repository.getFirstRecyclerModel()
            myResponse.value = response
        }
    }

    fun getPriemRecyclerModel(){
        viewModelScope.launch {
            val response = repository.getPriemRecyclerModel()
            myPriemResponse.value = response
        }
    }

}