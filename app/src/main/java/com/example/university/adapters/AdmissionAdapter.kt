package com.example.university.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.university.R
import com.example.university.model.InfoAdmission
import com.example.university.model.Priem
import com.example.university.screens.ActualFragmenrtDirections
import kotlinx.android.synthetic.main.first_recycler.view.*

class AdmissionAdapter  : RecyclerView.Adapter<AdmissionAdapter.ViewHolder>(){
    private  var item = emptyList<InfoAdmission>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_recycler_admission,
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

    fun setData(firstRecyclerModel: ArrayList<InfoAdmission>){
        item = firstRecyclerModel
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.title)
        val description: TextView = itemView.findViewById(R.id.description)

        fun bind(model: InfoAdmission){
            title.text = model.title
            description.text = model.description

        }
    }
}