package ru.ef.university.screens

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_splash.*
import kotlinx.android.synthetic.main.activity_splash.view.*
import ru.ef.university.R
import ru.ef.university.repository.Repository
import ru.ef.university.viewModel.MainViewModel
import ru.ef.university.viewModel.MainViewModelFactory

class SplashActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    val repository = Repository()
    val viewModelFactory = MainViewModelFactory(repository)
    val version = ru.ef.university.BuildConfig.VERSION_NAME

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        if( !hasConnection(applicationContext))
        {
            /*Toast.makeText(this, "Нет соединения с интернетом", ).show()
            this.finish()*/
            toast.text = "Нет соединения с Интернетом. \nПодключитесь к Интернету и попробуйте еще раз."
            toast.setTextColor(Color.BLACK)
            toast.setTextSize(16f)
            toast.setBackgroundResource(R.color.white)
            toast.visibility = View.VISIBLE
            photo.setImageResource(R.drawable.ic_wifi_x_thin_svgrepo_com)
        }else{

            viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

            viewModel.getFirstRecyclerModel()
            viewModel.myFirstResponse.observe(this, Observer { response->

                Glide.with(applicationContext!!).load(response.body()?.contactsInformation?.photo)
                    .apply(RequestOptions.bitmapTransform(RoundedCorners(1)))
                    .into(photo)

                if(response.body()?.version == version){
                    println(" Код: ${response.body()?.version}")
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()

                }else{
                    println(" Код: ${response.body()?.version}")
                    //Toast.makeText(applicationContext,"Обновите приложение",Toast.LENGTH_LONG).show()
                    toast.text = "Обновите приложение"
                    toast.visibility = View.VISIBLE
                }

            })
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
        wifiInfo = cm.getActiveNetworkInfo()
        if (wifiInfo != null && wifiInfo.isConnected())
        {
            return true;
        }
        return false;
    }


}