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
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import ru.ef.university.R
import ru.ef.university.viewModel.MainViewModel
import ru.ef.university.viewModel.MainViewModelFactory
import ru.ef.university.adapters.ActualFirstAdapter
import ru.ef.university.adapters.PagerAdapter
import ru.ef.university.adapters.PriemAdapter
import ru.ef.university.databinding.ActualFragmenrtBinding
import ru.ef.university.repository.Repository
import kotlinx.android.synthetic.main.actual_fragmenrt.*
import java.util.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class ActualFragmenrt : Fragment() {
    private lateinit var viewModel: MainViewModel
    private  var _binding: ActualFragmenrtBinding?=null
    private  val binding get() = _binding!!
    private val adapter by lazy { ActualFirstAdapter()}
    private val priemAdapter by lazy { PriemAdapter() }
    val repository = Repository()
    val viewModelFactory = MainViewModelFactory(repository)
    var position = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ActualFragmenrtBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt("HEADER", position)
        super.onSaveInstanceState(outState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(savedInstanceState != null){
            position = savedInstanceState.getInt("HEADER")
            //binding.container.scrollTo()
        }else{

        }

        if( !hasConnection(requireContext()))
        {
            activity?.finish()
            val intent = Intent(requireContext(), SplashActivity::class.java)
            startActivity(intent)
        }else{

        val firstRecycler = binding.firstRecycler
        firstRecycler.adapter = adapter
            val layoutManager = LinearLayoutManager(
                context
            )
            layoutManager.orientation = RecyclerView.HORIZONTAL
            layoutManager.stackFromEnd = false
            firstRecycler.setLayoutManager(layoutManager)

        setPriemAdapter()
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        addHeaderRecycler()
        addImageText()
        addPriemRecycler()
        goToImageText()}

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
        val layoutManager = LinearLayoutManager(
            context
        )
        layoutManager.orientation = RecyclerView.HORIZONTAL
        layoutManager.stackFromEnd = false
        priemRecycler.setLayoutManager(layoutManager)

    }

    fun addImageText(){
        viewModel.getFirstRecyclerModel()
        viewModel.myFirstResponse.observe(this, Observer { response->

            Glide.with(context!!).load(response.body()?.projectText?.imgMin)
                //.apply(RequestOptions.bitmapTransform(RoundedCorners(20)))
                .placeholder(R.drawable.ic_loading_svgrepo_com)
                .into(binding.projectText)
            binding.titleProject.text = response.body()?.projectText?.title

            Glide.with(context!!).load(response.body()?.imageTextSecond?.imgMin)
                //.apply(RequestOptions.bitmapTransform(RoundedCorners(20)))
                .placeholder(R.drawable.ic_loading_svgrepo_com)
                .into(binding.imageTextSecond)

            binding.titleSecond.text = response.body()?.imageTextSecond?.title
            binding.titleProject.text = response.body()?.imageTextSecond?.title

            //val adapter = PagerAdapter(response.body()?.photoList)

            val pagerAdapter = activity?.let {
                PagerAdapter(
                    requireContext(),
                    response.body()?.photoList as ArrayList<String>,
                )
            }
            binding.pager.apply {
                this.adapter = pagerAdapter
                setPageTransformer(false,
                    ru.ef.university.CustPagerTransformer(requireContext())
                )
                clipToPadding = false
                setPadding(50, 0, 50, 0)
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
        if( !hasConnection(requireContext()))
        {
            Toast.makeText(requireContext(), "Нет соединения с интернетом", Toast.LENGTH_LONG).show()

        }else{

        binding.constraintFirstProject.setOnClickListener {
            findNavController().navigate(R.id.action_actualFragmenrt_to_projectTextFragment)
            position = 2
        }

        binding.constraintSecondImage.setOnClickListener {
            findNavController().navigate(R.id.action_actualFragmenrt_to_secondImageTextFragment)
            position = 3
        }}

    }

}