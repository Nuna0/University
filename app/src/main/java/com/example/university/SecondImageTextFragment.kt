package com.example.university

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.university.adapters.ActualFirstAdapter
import com.example.university.adapters.SecondImageTextAdapter
import com.example.university.databinding.FragmentHeaderOpenBinding
import com.example.university.databinding.FragmentSecondImageTextBinding
import com.example.university.repository.Repository
import com.example.university.screens.AdmissionFragmentArgs
import com.example.university.viewModel.MainViewModel
import com.example.university.viewModel.MainViewModelFactory
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer

import androidx.annotation.NonNull

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener

import android.R

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

class SecondImageTextFragment : Fragment() {
    private  var _binding: FragmentSecondImageTextBinding?=null
    private  val binding get() = _binding!!
    private val repository = Repository()
    private val viewModelFactory = MainViewModelFactory(repository)
    private lateinit var viewModel: MainViewModel
    private val adapter by lazy { SecondImageTextAdapter() }
    //private  val args by navArgs <SecondImageTextFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSecondImageTextBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //adapter.setData(args.currentImageSecond?.infoFaculty)
        val recycler = binding.recycler
        recycler.adapter = adapter
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.getImageSecondModel()
        viewModel.myImageSecondResponse.observe(this, Observer { response->
            adapter.setData(response)
        })
        viewModel.getFirstRecyclerModel()
        viewModel.myFirstResponse.observe(this, Observer { response->
            val youTubePlayerView: YouTubePlayerView = binding.youtubePlayer
            lifecycle.addObserver(youTubePlayerView)

            youTubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    val videoId = response.body()?.facultyInformation?.url
                    if (videoId != null) {
                        youTubePlayer.cueVideo(videoId, 0f)
                    }
                }
            })

            response.body()?.facultyInformation?.let { adapter.setData(it.infoFaculty) }
            recycler.adapter = adapter
        })
        val toolbar = binding.toolbar
        binding.arrowBack.setOnClickListener {
            findNavController().popBackStack()
//            requireActivity().supportFragmentManager.popBackStack()
            // findNavController().navigate(com.example.university.R.id.action_secondImageTextFragment_to_actualFragmenrt)
        }
    }

}