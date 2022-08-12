package com.example.university.screens

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
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

        if( !hasConnection(requireContext()))
        {
            activity?.finish()
            Toast.makeText(requireContext(), "Нет соединения с интернетом", Toast.LENGTH_LONG).show()
            getActivity()?.finish()

        }else {
            binding.arrowBack.setOnClickListener {
                findNavController().popBackStack()
                //findNavController().navigate(com.example.university.R.id.action_infoAppFragment_to_infoFragment)
            }

            viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

            addInfo()
        }
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