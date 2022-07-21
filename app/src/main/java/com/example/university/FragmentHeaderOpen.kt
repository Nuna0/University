package com.example.university

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.university.databinding.FragmentHeaderOpenBinding

class FragmentHeaderOpen : Fragment() {
    private  var _binding: FragmentHeaderOpenBinding?=null
    private  val binding get() = _binding!!
    private  val args by navArgs<FragmentHeaderOpenArgs>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHeaderOpenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Glide.with(context!!).load(args.currentHeader.imgMin)
            .apply(RequestOptions.centerCropTransform())
            .into(binding.image)

        binding.text.setText(args.currentHeader.title)
        binding.description.setText(args.currentHeader.description)

        val toolbar = binding.toolbar
        toolbar.setNavigationOnClickListener {
            findNavController().navigate(R.id.action_fragmentHeaderOpen_to_actualFragmenrt)
        }
    }

}