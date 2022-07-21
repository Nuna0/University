package com.example.university.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.university.R

class SlidingImageAdapter (
    var context: Context,
    var arrayImages: ArrayList<String>
) : PagerAdapter() {

    val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val imageLayout = inflater.inflate(
            R.layout.item_banner_images,
            container,
            false
        )

        val image = imageLayout.findViewById<ImageView>(R.id.imageBanner)

        Glide.with(context).load(arrayImages[position])
            .apply(RequestOptions.bitmapTransform(RoundedCorners(1)))
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