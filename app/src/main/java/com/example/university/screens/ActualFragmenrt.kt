package com.example.university.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.university.MainViewModel
import com.example.university.MainViewModelFactory
import com.example.university.adapters.ActualFirstAdapter
import com.example.university.adapters.PriemAdapter
import com.example.university.databinding.ActualFragmenrtBinding
import com.example.university.repository.Repository


class ActualFragmenrt : Fragment() {
    private lateinit var viewModel: MainViewModel
    private  var _binding: ActualFragmenrtBinding?=null
    private  val binding get() = _binding!!
    private val adapter by lazy { ActualFirstAdapter()}
    private val priemAdapter by lazy { PriemAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ActualFragmenrtBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val firstRecycler = binding.firstRecycler
        firstRecycler.adapter = adapter

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.getFirstRecyclerModel()
        viewModel.myResponse.observe(this, Observer { response->
            adapter.setData(response)
        })

        setPriemAdapter()
        viewModel.getPriemRecyclerModel()
        viewModel.myPriemResponse.observe(this, Observer { response->
            priemAdapter.setData(response)
        })
    }
    fun setPriemAdapter() {
        val priemRecycler = binding.secondRecycler
        priemRecycler.adapter = priemAdapter

    }
}