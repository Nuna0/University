package com.example.university.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.university.R
import com.example.university.databinding.FragmentAdmissionBinding

class AdmissionFragment : Fragment() {

    private  var _binding: FragmentAdmissionBinding?=null
    private  val binding get() = _binding!!
    private  val args by navArgs<AdmissionFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAdmissionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Glide.with(context!!).load(args.currentQuestion.imgMin)
            .apply(RequestOptions.bitmapTransform(RoundedCorners(14)))
            .into(binding.image)

        binding.text.setText(args.currentQuestion.title)

        val toolbar = binding.toolbar
        toolbar.setNavigationOnClickListener {
            findNavController().navigate(R.id.action_admissionFragment_to_actualFragmenrt)
        }
    }
    
}