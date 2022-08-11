package com.example.university.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.university.R
import com.example.university.model.BachelorPrograms
import com.example.university.screens.ProgramsFragmentDirections
import kotlinx.android.synthetic.main.bachelor_recycler.view.*

class BachelorAdapter: RecyclerView.Adapter<BachelorAdapter.BachelorViewHolder>(){
    private  var item = emptyList<BachelorPrograms>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BachelorViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.bachelor_recycler,parent, false)
        return BachelorViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: BachelorViewHolder, position: Int) {
        val currentItem = item[position]
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int {
        return item.size
    }

    fun setData(firstRecyclerModel: ArrayList<BachelorPrograms>){
        item = firstRecyclerModel
        notifyDataSetChanged()
    }

    class BachelorViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val codOfFaculty: TextView = itemView.findViewById(R.id.cod_of_faculty)
        val directionOfFaculty: TextView = itemView.findViewById(R.id.direction_of_faculty)
        val info_direction: TextView = itemView.findViewById(R.id.direction_info)

        fun bind(model: BachelorPrograms){
            /*Glide.with(itemView.context).load(model.imgMin)
                .apply(RequestOptions.bitmapTransform(RoundedCorners(14)))
                .into(image)*/

            codOfFaculty.text = model.id
            directionOfFaculty.text = model.title
            info_direction.text = "Форма обучения: ${model.format} (${model.year}) \nКоличество бюджетных мест: ${model.countBudget} \nКоличество платных мест: ${model.countPaid} (${model.price}₽) \n" +
                    "Направления подготовки: " +
                    "${model.direction}"

            /*itemView.linear.setOnClickListener {
                val action =ProgramsFragmentDirections.actionProgramsFragmentToBachelorFragment(model)
                itemView.findNavController().navigate(action)
            }*/
        }
    }
}