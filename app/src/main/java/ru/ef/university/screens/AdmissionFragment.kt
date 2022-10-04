package ru.ef.university.screens

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import ru.ef.university.screens.AdmissionFragmentArgs
import ru.ef.university.adapters.AdmissionAdapter
import ru.ef.university.databinding.FragmentAdmissionBinding

class AdmissionFragment : Fragment() {

    private  var _binding: FragmentAdmissionBinding?=null
    private  val binding get() = _binding!!
    private  val args by navArgs <AdmissionFragmentArgs>()
    private val adapter by lazy { AdmissionAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAdmissionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        if( !hasConnection(requireContext()))
        {
            activity?.finish()
            val intent = Intent(requireContext(), SplashActivity::class.java)
            startActivity(intent)


        }else {
        adapter.setData(args.currentQuestion.infoAdmission)
        binding.recycler.adapter = adapter

        val toolbar = binding.toolbar
        toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        }
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