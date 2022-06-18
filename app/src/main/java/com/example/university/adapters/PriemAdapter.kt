package com.example.university.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.university.FragmentHeaderOpenDirections
import com.example.university.R
import com.example.university.model.Priem
import com.example.university.screens.ActualFragmenrtDirections
import kotlinx.android.synthetic.main.first_recycler.view.*

class PriemAdapter : RecyclerView.Adapter<PriemAdapter.PriemViewHolder>(){
    private  var item = emptyList<Priem>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PriemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.second_recycler,parent, false)
        return PriemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PriemViewHolder, position: Int) {
        val currentItem = item[position]
        holder.bind(currentItem)

    }

    override fun getItemCount(): Int {
        return item.size
    }

    fun setData(firstRecyclerModel: ArrayList<Priem>){
        item = firstRecyclerModel
        notifyDataSetChanged()
    }

    class PriemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.image)

        fun bind(model: Priem){
            Glide.with(itemView.context).load(model.imgMin)
                .apply(RequestOptions.bitmapTransform(RoundedCorners(14)))
                .into(image)

            itemView.constraint.setOnClickListener {
                val action = ActualFragmenrtDirections.actionActualFragmenrtToAdmissionFragment(model)
                itemView.findNavController().navigate(action)

            }

        }

    }
}