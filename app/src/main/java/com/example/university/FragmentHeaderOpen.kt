package com.example.university

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.university.adapters.SlidingImageAdapter
import com.example.university.databinding.FragmentHeaderOpenBinding
import kotlinx.android.synthetic.main.actual_fragmenrt.*
import kotlinx.android.synthetic.main.actual_fragmenrt.indicator
import kotlinx.android.synthetic.main.actual_fragmenrt.pager
import kotlinx.android.synthetic.main.fragment_header_open.*

class FragmentHeaderOpen : DialogFragment() {
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
        //val adapter = PagerAdapter(args.currentHeader.imgMax)

        if( !hasConnection(requireContext()))
        {
            activity?.finish()
            Toast.makeText(requireContext(), "Нет соединения с интернетом", Toast.LENGTH_LONG).show()
            getActivity()?.finish()

        }else {
            val pagerAdapter = SlidingImageAdapter(
                requireContext(),
                args.currentHeader.imgMax
            )
            pager.apply {
                this.adapter = pagerAdapter
                setPageTransformer(false, CustPagerTransformer(requireContext()))
                clipToPadding = false
                //setPadding(0, 0, 0, 0)
                //pageMargin = 20
            }

            indicator.setViewPager(pager)
            val density = resources.displayMetrics.density
            indicator.radius = density * 3

            /*val toolbar = binding.toolbar
        toolbar.setNavigationOnClickListener {
            findNavController().navigate(R.id.action_fragmentHeaderOpen_to_actualFragmenrt)
        }*/

            binding.icCancel.setOnClickListener {
                // requireActivity().supportFragmentManager.beginTransaction().remove(this).commit()
                //requireActivity().supportFragmentManager.popBackStack()
                //findNavController().navigate(R.id.action_fragmentHeaderOpen_to_actualFragmenrt)
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