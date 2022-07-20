package com.example.university.screens

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.university.R
import com.example.university.databinding.ActualFragmenrtBinding
import com.example.university.databinding.FragmentInfoBinding

class InfoFragment : Fragment() {

    private  var _binding: FragmentInfoBinding?=null
    private  val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnToTlgm.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/Dgu_ef_bot"))
            startActivity(i)
        }
    }
}

