package com.example.university.adapters

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.example.university.GlideApp
import com.example.university.R
import com.example.university.databinding.ItemPagerBinding

class PagerAdapter(
    var context: Context,
    var arrayImages: ArrayList<String>,
    /*val swipetoclose: () -> Unit*/
) : PagerAdapter() {

    val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val imageLayout = inflater.inflate(
            R.layout.item_banner_images,
            container,
            false
        )

        val image = imageLayout.findViewById<ImageView>(R.id.imageBanner)
        val cardView = imageLayout.findViewById<CardView>(R.id.cardView)


        GlideApp.with(context)
            .asBitmap()
            .apply(RequestOptions.bitmapTransform(RoundedCorners(20)))
            .placeholder(R.drawable.ic_loading_svgrepo_com)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .load(arrayImages[position])
            .into(image)

        container.addView(imageLayout, 0)

        return imageLayout
    }

    override fun destroyItem(collection: ViewGroup, position: Int, view: Any) {
        collection.removeView(view as View)
    }

    override fun isViewFromObject(view: View, obj: Any): Boolean = view == obj


    override fun getCount(): Int = arrayImages.size
}