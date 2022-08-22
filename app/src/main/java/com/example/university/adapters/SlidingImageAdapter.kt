package com.example.university.adapters

import android.content.ContentValues.TAG
import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.palette.graphics.Palette
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.university.R
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.example.university.GlideApp
import com.example.university.PaletteGlideModule
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions.Companion.default
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.sql.DataSource


class SlidingImageAdapter(
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

        /*val bitmap = (image.getDrawable() as BitmapDrawable).bitmap
        val pixel = bitmap.getPixel(x, y)
        container.setBackgroundResource(pixel)*/


        GlideApp.with(context)
            .asBitmap()
            .apply(RequestOptions.bitmapTransform(RoundedCorners(20)))
            .placeholder(R.drawable.ic_loading_svgrepo_com)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .listener(object : RequestListener<Bitmap> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: com.bumptech.glide.request.target.Target<Bitmap>?,
                    isFirstResource: Boolean
                ): Boolean {
                    Log.i(TAG, "Error while loading picture: $e")
                    return false
                }

                override fun onResourceReady(
                    resource: Bitmap?,
                    model: Any?,
                    target: com.bumptech.glide.request.target.Target<Bitmap>?,
                    dataSource: com.bumptech.glide.load.DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    if (resource != null) {
                        val palette: Palette = Palette.from(resource).generate()
                        val color = palette.dominantSwatch?.rgb ?: R.color.default_title_background
                        cardView.setCardBackgroundColor(color)
                    }
                    return false
                }

            })
            .load(arrayImages[position])
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