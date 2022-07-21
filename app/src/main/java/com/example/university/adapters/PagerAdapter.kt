package com.example.university.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.university.databinding.ItemPagerBinding

class PagerAdapter(private val photo: List<String>?) :
    RecyclerView.Adapter<PagerAdapter.EventViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val itemView = ItemPagerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EventViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) =
        holder.bind(photo!![position])

    override fun getItemCount(): Int = photo!!.size


    class EventViewHolder(private val itemBinding: ItemPagerBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(
            photo: String,
        ) {
            Glide.with(itemView).load(photo)
                .apply(RequestOptions.bitmapTransform(RoundedCorners(1)))
                .into(itemBinding.image)

        }
    }
}