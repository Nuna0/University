package com.example.university.screens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.university.R
import com.example.university.repository.Repository
import com.example.university.viewModel.MainViewModel
import com.example.university.viewModel.MainViewModelFactory
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    val repository = Repository()
    val viewModelFactory = MainViewModelFactory(repository)
    val version:String = "1.0"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        viewModel.getFirstRecyclerModel()
        viewModel.myFirstResponse.observe(this, Observer { response->

            if(response.body()?.version?.codeOne == response.body()?.version?.codeOne){
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()

            }else{
                Toast.makeText(applicationContext,"Обновите приложение",Toast.LENGTH_LONG).show()
            }

            Glide.with(applicationContext!!).load(response.body()?.contactsInformation?.photo)
                .apply(RequestOptions.bitmapTransform(RoundedCorners(1)))
                .into(photo)
        })
    }


}