package com.example.university.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.university.viewModel.MainViewModel
import com.example.university.viewModel.MainViewModelFactory
import com.example.university.adapters.BachelorAdapter
import com.example.university.adapters.MagistracyAdapter
import com.example.university.adapters.SpecialtyAdapter
import com.example.university.databinding.ProgramsFragmentBinding
import com.example.university.repository.Repository


class ProgramsFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private  var _binding: ProgramsFragmentBinding?=null
    private  val binding get() = _binding!!
    private val bacheloradapter by lazy { BachelorAdapter() }
    private val specialtyadapter by lazy { SpecialtyAdapter() }
    private val magistracyadapter by lazy { MagistracyAdapter() }
    val repository = Repository()
    val viewModelFactory = MainViewModelFactory(repository)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = ProgramsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        setBachelorAdapter()
        addBachelorRecycler()
    }

    fun setBachelorAdapter(){
        val bachelorRecycler = binding.bachelor
        bachelorRecycler.adapter = bacheloradapter
        val specialityRecycler = binding.speciality
        specialityRecycler.adapter = specialtyadapter
        val magistracyRecycler = binding.magistracy
        magistracyRecycler.adapter = magistracyadapter
    }

    fun addBachelorRecycler(){
        viewModel.getBachelorPrograms()
        viewModel.myBachelorProgramsResponse.observe(this, Observer { response->
            bacheloradapter.setData(response)
        })

        viewModel.getSpecialtyPrograms()
        viewModel.mySpecialtyProgramsResponse.observe(this, Observer { response->
            specialtyadapter.setData(response)
        })

        viewModel.getMagistracyPrograms()
        viewModel.myMagistracyProgramsResponse.observe(this, Observer {response->
            magistracyadapter.setData(response)
        })
    }

}