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
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import ru.ef.university.viewModel.MainViewModel
import ru.ef.university.viewModel.MainViewModelFactory
import ru.ef.university.adapters.BachelorAdapter
import ru.ef.university.adapters.MagistracyAdapter
import ru.ef.university.adapters.SpecialtyAdapter
import ru.ef.university.databinding.ProgramsFragmentBinding
import ru.ef.university.repository.Repository


class ProgramsFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private  var _binding: ProgramsFragmentBinding?=null
    private  val binding get() = _binding!!
    private val bacheloradapter by lazy { BachelorAdapter() }
    private val specialtyadapter by lazy { SpecialtyAdapter() }
    private val magistracyadapter by lazy { MagistracyAdapter() }
    val repository = Repository()
    val viewModelFactory = MainViewModelFactory(repository)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = ProgramsFragmentBinding.inflate(inflater, container, false)
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
            viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

            setBachelorAdapter()
            addBachelorRecycler()
        }
    }

    fun setBachelorAdapter(){
        val bachelorRecycler = binding.bachelor
        bachelorRecycler.adapter = bacheloradapter
        val specialityRecycler = binding.speciality
        specialityRecycler.adapter = specialtyadapter
        val magistracyRecycler = binding.magistracy
        magistracyRecycler.adapter = magistracyadapter
    }

    fun addBachelorRecycler(){
        viewModel.getBachelorPrograms()
        viewModel.myBachelorProgramsResponse.observe(this, Observer { response->
            bacheloradapter.setData(response)
        })

        viewModel.getSpecialtyPrograms()
        viewModel.mySpecialtyProgramsResponse.observe(this, Observer { response->
            specialtyadapter.setData(response)
        })

        viewModel.getMagistracyPrograms()
        viewModel.myMagistracyProgramsResponse.observe(this, Observer {response->
            magistracyadapter.setData(response)
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