package com.example.university.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.university.MainViewModel
import com.example.university.MainViewModelFactory
import com.example.university.R
import com.example.university.adapters.ActualFirstAdapter
import com.example.university.adapters.BachelorAdapter
import com.example.university.databinding.ActualFragmenrtBinding
import com.example.university.databinding.ProgramsFragmentBinding
import com.example.university.repository.Repository


class ProgramsFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private  var _binding: ProgramsFragmentBinding?=null
    private  val binding get() = _binding!!
    private val bacheloradapter by lazy { BachelorAdapter() }
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
    }

    fun addBachelorRecycler(){
        viewModel.getBachelorPrograms()
        viewModel.myBachelorProgramsResponse.observe(this, Observer { response->
            bacheloradapter.setData(response)
        })
    }

}