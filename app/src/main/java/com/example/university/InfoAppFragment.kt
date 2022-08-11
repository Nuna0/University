package com.example.university

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.university.databinding.FragmentInfoAppBinding
import com.example.university.repository.Repository
import com.example.university.viewModel.MainViewModel
import com.example.university.viewModel.MainViewModelFactory

class InfoAppFragment : Fragment() {

    private  var _binding: FragmentInfoAppBinding?=null
    private  val binding get() = _binding!!

    private lateinit var viewModel: MainViewModel
    val repository = Repository()
    val viewModelFactory = MainViewModelFactory(repository)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentInfoAppBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.arrowBack.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
            //findNavController().navigate(com.example.university.R.id.action_infoAppFragment_to_infoFragment)
        }

        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        addInfo()
    }

    fun addInfo(){
        viewModel.getFirstRecyclerModel()
        viewModel.myFirstResponse.observe(this, Observer { response ->
            with(binding) {

                Glide.with(context!!).load(response.body()?.contactsInformation?.photo)
                    .apply(RequestOptions.bitmapTransform(RoundedCorners(1)))
                    .into(photo)
            }
        })
    }

}