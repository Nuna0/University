package ru.ef.university.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import ru.ef.university.screens.BachelorFragmentArgs
import ru.ef.university.R
import ru.ef.university.databinding.FragmentBachelorBinding

class BachelorFragment : Fragment() {

    private  var _binding: FragmentBachelorBinding?=null
    private  val binding get() = _binding!!
    private  val args by navArgs<BachelorFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentBachelorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(context!!).load(args.currentBachelor.imgMin)
            .apply(RequestOptions.bitmapTransform(RoundedCorners(20)))
            .into(binding.image)

        val toolbar = binding.toolbar
        toolbar.setNavigationOnClickListener {
            findNavController().navigate(R.id.action_bachelorFragment_to_programsFragment)
        }
    }

}