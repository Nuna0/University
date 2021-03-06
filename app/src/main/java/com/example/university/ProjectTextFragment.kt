package com.example.university

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.university.databinding.FragmentHeaderOpenBinding
import com.example.university.databinding.FragmentProjectTextBinding


class ProjectTextFragment : Fragment() {
    private  var _binding: FragmentProjectTextBinding?=null
    private  val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentProjectTextBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val toolbar = binding.toolbar
        toolbar.setNavigationOnClickListener {
            findNavController().navigate(R.id.action_projectTextFragment_to_actualFragmenrt)
        }
    }


}