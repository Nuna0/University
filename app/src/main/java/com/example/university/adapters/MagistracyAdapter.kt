package com.example.university.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.university.R
import com.example.university.model.BachelorPrograms
import com.example.university.model.MagistracyPrograms
import com.example.university.screens.ProgramsFragmentDirections
import kotlinx.android.synthetic.main.bachelor_recycler.view.*

class MagistracyAdapter : RecyclerView.Adapter<MagistracyAdapter.MagistracyViewHolder>(){
    private  var item = emptyList<MagistracyPrograms>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MagistracyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.bachelor_recycler,parent, false)
        return MagistracyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MagistracyViewHolder, position: Int) {
        val currentItem = item[position]
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int {
        return item.size
    }

    fun setData(firstRecyclerModel: ArrayList<MagistracyPrograms>){
        item = firstRecyclerModel
        notifyDataSetChanged()
    }

    class MagistracyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val codOfFaculty: TextView = itemView.findViewById(R.id.cod_of_faculty)
        val directionOfFaculty: TextView = itemView.findViewById(R.id.direction_of_faculty)
        val info_direction: TextView = itemView.findViewById(R.id.direction_info)

        fun bind(model: MagistracyPrograms){
            /*Glide.with(itemView.context).load(model.imgMin)
                .apply(RequestOptions.bitmapTransform(RoundedCorners(14)))
                .into(image)*/

            codOfFaculty.text = "38.03.01"
            directionOfFaculty.text = model.title
            info_direction.text = "Форма обучения: ${model.format} (${model.year}) \nКоличество бюджетных мест: ${model.countBudget} \nКоличество платных мест: ${model.countPaid} (${model.price}₽)"

            /*itemView.linear.setOnClickListener {
                val action = ProgramsFragmentDirections.actionProgramsFragmentToBachelorFragment(model)
                itemView.findNavController().navigate(action)
            }*/
        }
    }
}