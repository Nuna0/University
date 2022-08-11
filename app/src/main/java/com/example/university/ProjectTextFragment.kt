package com.example.university

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat.canScrollVertically
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.INVALID_OFFSET
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.university.adapters.ActualFirstAdapter
import com.example.university.adapters.InfoProjectAdapter
import com.example.university.databinding.FragmentHeaderOpenBinding
import com.example.university.databinding.FragmentProjectTextBinding
import com.example.university.repository.Repository
import com.example.university.viewModel.MainViewModel
import com.example.university.viewModel.MainViewModelFactory
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView


class ProjectTextFragment : Fragment() {
    private  var _binding: FragmentProjectTextBinding?=null
    private  val binding get() = _binding!!

    private lateinit var viewModel: MainViewModel
    val repository = Repository()
    val viewModelFactory = MainViewModelFactory(repository)
    private val adapter by lazy { InfoProjectAdapter() }


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

        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        val toolbar = binding.toolbar
        binding.arrowBack.setOnClickListener {
            //requireActivity().supportFragmentManager.popBackStack()
            //findNavController().navigate(R.id.action_projectTextFragment_to_actualFragmenrt)
            findNavController().popBackStack()
        }

        viewModel.getFirstRecyclerModel()
        viewModel.myFirstResponse.observe(this, Observer { response->
            val youTubePlayerView: YouTubePlayerView = binding.youtubePlayer
            lifecycle.addObserver(youTubePlayerView)

            youTubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    val videoId = response.body()?.projectText?.url
                    if (videoId != null) {
                        youTubePlayer.cueVideo(videoId, 0f)
                    }
                }
            })

            response.body()?.projectText?.infoProject?.let { adapter.setData(it) }
            binding.recycler.adapter = adapter

        })

    }



}