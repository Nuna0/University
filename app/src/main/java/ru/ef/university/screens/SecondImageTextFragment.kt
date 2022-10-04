package ru.ef.university.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerFullScreenListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import ru.ef.university.adapters.SecondImageTextAdapter
import ru.ef.university.databinding.FragmentSecondImageTextBinding
import ru.ef.university.repository.Repository
import ru.ef.university.viewModel.MainViewModel
import ru.ef.university.viewModel.MainViewModelFactory
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.ui.DefaultPlayerUiController
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions


class SecondImageTextFragment : Fragment() {
    private  var _binding: FragmentSecondImageTextBinding?=null
    private  val binding get() = _binding!!
    private val repository = Repository()
    private val viewModelFactory = MainViewModelFactory(repository)
    private lateinit var viewModel: MainViewModel
    private val adapter by lazy { SecondImageTextAdapter() }
    lateinit var  youTubePlayerView: YouTubePlayerView
    lateinit var  youTubeFullListener: YouTubePlayerFullScreenListener

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

        if( !hasConnection(requireContext()))
        {
            activity?.finish()
            val intent = Intent(requireContext(), SplashActivity::class.java)
            startActivity(intent)
        }else {
            //adapter.setData(args.currentImageSecond?.infoFaculty)
            viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
            viewModel.getImageSecondModel()
            viewModel.myImageSecondResponse.observe(this, Observer { response ->
                adapter.setData(response)
            })
            setYouTubeVideo()

            val toolbar = binding.toolbar
            binding.arrowBack.setOnClickListener {
                findNavController().popBackStack()
//            requireActivity().supportFragmentManager.popBackStack()
                // findNavController().navigate(com.example.university.R.id.action_secondImageTextFragment_to_actualFragmenrt)
            }
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            youTubePlayerView.enterFullScreen()
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            youTubePlayerView.exitFullScreen()
        }
    }


    fun setYouTubeVideo(){
        viewModel.getFirstRecyclerModel()
        viewModel.myFirstResponse.observe(this, Observer { response ->
            youTubePlayerView = binding.youtubePlayer
            lifecycle.addObserver(youTubePlayerView)

            youTubePlayerView.addYouTubePlayerListener(object :
                AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    val videoId = response.body()?.facultyInformation?.url
                    if (videoId != null) {
                        youTubePlayer.cueVideo(videoId, 0f)
                    }

                    /*val defaultPlayerUiController =
                        DefaultPlayerUiController(youTubePlayerView, youTubePlayer)
                    youTubePlayerView.setCustomPlayerUi(defaultPlayerUiController.rootView)*/
                }
            })

           /* val options:IFramePlayerOptions = IFramePlayerOptions.Builder().controls(0).build()
            youTubePlayerView.initialize(object:AbstractYouTubePlayerListener(){
                override fun onReady(youTubePlayer: YouTubePlayer){
                    val defaultPlayerUiController =
                        DefaultPlayerUiController(youTubePlayerView, youTubePlayer)
                    youTubePlayerView.setCustomPlayerUi(defaultPlayerUiController.rootView)
                } }, options)

             youTubePlayerView.addFullScreenListener( object :YouTubePlayerFullScreenListener{
                 override fun onYouTubePlayerEnterFullScreen() {
                     youTubePlayerView.enterFullScreen()

                 }

                 override fun onYouTubePlayerExitFullScreen() {
                     youTubePlayerView.exitFullScreen()
                 }
             })*/
            val recycler = binding.recycler
            recycler.adapter = adapter
            response.body()?.facultyInformation?.let { adapter.setData(it.infoFaculty) }
            recycler.adapter = adapter
        })
    }
    fun  hasConnection(context: Context): Boolean
    {
        val cm: ConnectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        var wifiInfo: NetworkInfo? = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (wifiInfo != null && wifiInfo.isConnected())
        {
            return true;
        }
        wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (wifiInfo != null && wifiInfo.isConnected())
        {
            return true;
        }
        wifiInfo = cm.getActiveNetworkInfo();
        if (wifiInfo != null && wifiInfo.isConnected())
        {
            return true;
        }
        return false;
    }

}