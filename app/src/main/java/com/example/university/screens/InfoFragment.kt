package com.example.university.screens

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.university.R
import com.example.university.databinding.ActualFragmenrtBinding
import com.example.university.databinding.FragmentInfoBinding
import com.example.university.repository.Repository
import com.example.university.viewModel.MainViewModel
import com.example.university.viewModel.MainViewModelFactory
import android.content.pm.PackageManager
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions


class InfoFragment : Fragment() {

    private  var _binding: FragmentInfoBinding?=null
    private  val binding get() = _binding!!

    private lateinit var viewModel: MainViewModel
    val repository = Repository()
    val viewModelFactory = MainViewModelFactory(repository)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        addInfo()
        /*binding.btnToTlgm.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/Dgu_ef_bot"))
            startActivity(i)
        }*/

    }

    fun addInfo(){
        viewModel.getFirstRecyclerModel()
        viewModel.myFirstResponse.observe(this, Observer { response->
            with(binding){
                btnToWrite.setOnClickListener {
                    val i = Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/${response.body()?.contactsInformation?.tgBot}"))
                    startActivity(i)
                }

                val phone: String = response.body()?.contactsInformation?.number.toString()
                btnToPhone.setOnClickListener {
                    /*val intent = Intent(Intent.ACTION_CALL, Uri.parse(phone))
                    startActivity(intent)*/

                    val intent = Intent(Intent.ACTION_DIAL);
                    intent.data = Uri.parse("tel:$phone")
                    startActivity(intent)
                }

                Glide.with(context!!).load(response.body()?.contactsInformation?.photo)
                    .apply(RequestOptions.bitmapTransform(RoundedCorners(1)))
                    .into(binding.photo)

                imgTelegm.setOnClickListener{
                    val i = Intent(Intent.ACTION_VIEW, Uri.parse(response.body()?.contactsInformation?.tgChannel))
                    startActivity(i)
                }

                imgVK.setOnClickListener {
                    val i = Intent(Intent.ACTION_VIEW, Uri.parse(response.body()?.contactsInformation?.vk))
                    startActivity(i)
                }

                imgYouTube.setOnClickListener {
                    val i = Intent(Intent.ACTION_VIEW, Uri.parse(response.body()?.contactsInformation?.youtube))
                    startActivity(i)
                }

                contAboutApp.setOnClickListener {
                    findNavController().navigate(R.id.action_infoFragment_to_infoAppFragment)
                }

            }
        })
    }
}

