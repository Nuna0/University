package com.example.university.adapters

import android.R.attr
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.university.OnSwipeTouchListener
import com.example.university.R
import android.R.attr.y

import android.R.attr.x

import android.graphics.drawable.BitmapDrawable

import android.graphics.Bitmap




class SlidingImageAdapter (
    var context: Context,
    var arrayImages: ArrayList<String>
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
        /*val container = imageLayout.findViewById<ConstraintLayout>(R.id.main)

        val bitmap = (image.getDrawable() as BitmapDrawable).bitmap
        val pixel = bitmap.getPixel(x, y)
        container.setBackgroundResource(pixel)*/


        Glide.with(context).load(arrayImages[position])
            .apply(RequestOptions.bitmapTransform(RoundedCorners(20)))
            .placeholder(R.drawable.ic_loading_svgrepo_com)
            .into(image)

       /* container.setOnTouchListener(object: OnSwipeTouchListener(ctx = context) {
            override fun onSwipeLeft() {
                swipetoclose
            }
        })*/

        container.addView(imageLayout, 0)

        return imageLayout
    }

    override fun destroyItem(collection: ViewGroup, position: Int, view: Any) {
        collection.removeView(view as View)
    }

    override fun isViewFromObject(view: View, obj: Any): Boolean = view == obj


    override fun getCount(): Int = arrayImages.size

}