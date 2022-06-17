package com.example.university.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.university.model.FirstRecyclerModel
import com.example.university.R
import com.example.university.model.Header
import com.example.university.screens.ActualFragmenrt
import com.example.university.screens.ActualFragmenrtDirections
import kotlinx.android.synthetic.main.first_recycler.view.*

class ActualFirstAdapter:RecyclerView.Adapter<ActualFirstAdapter.ViewHolder>(){
    private  var item = emptyList<Header>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.first_recycler,parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = item[position]
        holder.bind(currentItem)

    }

    override fun getItemCount(): Int {
        return item.size
    }

    fun setData(firstRecyclerModel: ArrayList<Header>){
        item = firstRecyclerModel
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val image:ImageView = itemView.findViewById(R.id.image_ofFirstRec)

        fun bind(model: Header){
            Glide.with(itemView.context).load(model.imgMin)
                .apply(RequestOptions.centerCropTransform())
                .into(image)

            itemView.constraint.setOnClickListener {
                val action = ActualFragmenrtDirections.actionActualFragmenrtToFragmentHeaderOpen(model)
                itemView.findNavController().navigate(action)

            }


        }

    }
}