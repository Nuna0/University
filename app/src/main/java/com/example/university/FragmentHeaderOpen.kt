package com.example.university

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.university.adapters.SlidingImageAdapter
import com.example.university.databinding.FragmentHeaderOpenBinding
import kotlinx.android.synthetic.main.actual_fragmenrt.*
import kotlinx.android.synthetic.main.actual_fragmenrt.indicator
import kotlinx.android.synthetic.main.actual_fragmenrt.pager
import kotlinx.android.synthetic.main.fragment_header_open.*

class FragmentHeaderOpen : Fragment() {
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

        val pagerAdapter = SlidingImageAdapter(requireContext(),
            args.currentHeader.imgMax
            )
        pager.apply {
            this.adapter = pagerAdapter
            setPageTransformer(false, CustPagerTransformer(requireContext()))
            clipToPadding = false
            //setPadding(0, 0, 0, 0)
            //pageMargin = 20
        }
        val current = pager.currentItem
        val totalItems = pager.adapter?.count

        indicator.setViewPager(pager)
        val density = resources.displayMetrics.density
        indicator.radius = density * 3

        /*val toolbar = binding.toolbar
        toolbar.setNavigationOnClickListener {
            findNavController().navigate(R.id.action_fragmentHeaderOpen_to_actualFragmenrt)
        }*/

        /*last.setOnClickListener {

            if (totalItems != null) {
                if(current < totalItems - 1) {
                    pager.setCurrentItem(current + 1, true);
                }
            }

        }

        next.setOnClickListener {

            if(current != 0) {
                pager.setCurrentItem(current - 1, true);
            }
        }*/

        binding.icCancel.setOnClickListener {
            //dialog?.cancel()
           // requireActivity().supportFragmentManager.beginTransaction().remove(this).commit()
            requireActivity().supportFragmentManager.popBackStack()
            //findNavController().navigate(R.id.action_fragmentHeaderOpen_to_actualFragmenrt)

        }

    }

}