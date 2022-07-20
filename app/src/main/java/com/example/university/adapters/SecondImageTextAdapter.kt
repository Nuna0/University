package com.example.university.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.university.R
import com.example.university.model.InfoFaculty

class SecondImageTextAdapter : RecyclerView.Adapter<SecondImageTextAdapter.ViewHolder>(){
    private  var item = emptyList<InfoFaculty>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.recycler_second_image_text,
            parent,
            false
        )
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = item[position]
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int {
        return item.size
    }

    fun setData(firstRecyclerModel: ArrayList<InfoFaculty>){
        item = firstRecyclerModel
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.title)
        val image: ImageView = itemView.findViewById(R.id.image)
        val description: TextView = itemView.findViewById(R.id.description)

        fun bind(model: InfoFaculty?){
            title.text = model?.title
            description.text = model?.description
            Glide.with(itemView.context).load(model?.imgMin)
                .apply(RequestOptions.bitmapTransform(RoundedCorners(20)))
                .into(image)

        }
    }
}