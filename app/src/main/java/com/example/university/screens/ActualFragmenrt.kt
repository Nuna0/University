package com.example.university.screens

import android.app.AlertDialog
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.university.CustPagerTransformer
import com.example.university.R
import com.example.university.viewModel.MainViewModel
import com.example.university.viewModel.MainViewModelFactory
import com.example.university.adapters.ActualFirstAdapter
import com.example.university.adapters.PagerAdapter
import com.example.university.adapters.PriemAdapter
import com.example.university.adapters.SlidingImageAdapter
import com.example.university.databinding.ActualFragmenrtBinding
import com.example.university.repository.Repository
import kotlinx.android.synthetic.main.actual_fragmenrt.*
import java.util.*


class ActualFragmenrt : Fragment() {
    private lateinit var viewModel: MainViewModel
    private  var _binding: ActualFragmenrtBinding?=null
    private  val binding get() = _binding!!
    private val adapter by lazy { ActualFirstAdapter()}
    private val priemAdapter by lazy { PriemAdapter() }
    val repository = Repository()
    val viewModelFactory = MainViewModelFactory(repository)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ActualFragmenrtBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val firstRecycler = binding.firstRecycler
        firstRecycler.adapter = adapter
        setPriemAdapter()
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        addHeaderRecycler()
        addImageText()
        addPriemRecycler()
        goToImageText()

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

    fun setPriemAdapter() {
        val priemRecycler = binding.secondRecycler
        priemRecycler.adapter = priemAdapter

    }

    fun addImageText(){
        viewModel.getFirstRecyclerModel()
        viewModel.myFirstResponse.observe(this, Observer { response->
            /*Glide.with(context!!).load(response.body()?.imageText?.imgMin)
                .apply(RequestOptions.bitmapTransform(RoundedCorners(30)))
                .into(binding.imageText)
            binding.title.text = response.body()?.imageText?.title*/

            Glide.with(context!!).load(response.body()?.projectText?.imgMin)
                .apply(RequestOptions.bitmapTransform(RoundedCorners(20)))
                .into(binding.projectText)
            binding.titleProject.text = response.body()?.projectText?.title

            /*if(!response.isSuccessful){
                val builder = AlertDialog.Builder(requireContext())
                builder.setTitle("Важное сообщение!")
                    .setMessage("Проверьте подключение к интернету!")
                    .setPositiveButton("ОК") {
                            dialog, id ->  dialog.cancel()
                    }
            }*/
            Glide.with(context!!).load(response.body()?.imageTextSecond?.imgMin)
                .apply(RequestOptions.bitmapTransform(RoundedCorners(20)))
                .into(binding.imageTextSecond)

            binding.titleSecond.text = response.body()?.imageTextSecond?.title
            binding.titleProject.text = response.body()?.imageTextSecond?.title

            val adapter = PagerAdapter(response.body()?.photoList)

            val pagerAdapter = SlidingImageAdapter(requireContext(),
                response.body()?.photoList as ArrayList<String>
                /*swipetoclose = {}*/)
            binding.pager.apply {
                this.adapter = pagerAdapter
                setPageTransformer(false, CustPagerTransformer(requireContext()))
                clipToPadding = false
                setPadding(80, 0, 80, 0)
                pageMargin = 20
            }

            indicator.setViewPager(pager)
            val density = resources.displayMetrics.density
            indicator.radius = density * 3

        })

    }

    fun addHeaderRecycler(){
        viewModel.getHeaderRecyclerModel()
        viewModel.myResponse.observe(this, Observer { response->
            adapter.setData(response)
        })
    }

    fun addPriemRecycler(){
        viewModel.getPriemRecyclerModel()
        viewModel.myPriemResponse.observe(this, Observer { response->
            priemAdapter.setData(response)
        })
    }

    fun goToImageText(){
        /*binding.constraintFirstImage.setOnClickListener {
            findNavController().navigate(R.id.action_actualFragmenrt_to_firstImageTextFragment)
        }*/

        binding.constraintFirstProject.setOnClickListener {
            findNavController().navigate(R.id.action_actualFragmenrt_to_projectTextFragment)
        }

        binding.constraintSecondImage.setOnClickListener {
            findNavController().navigate(R.id.action_actualFragmenrt_to_secondImageTextFragment)
        }


    }

}