package com.bangkit.bahanbaku.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.bahanbaku.R
import com.bangkit.bahanbaku.data.remote.response.SnapFoodItem
import com.bangkit.bahanbaku.databinding.ItemFoodBinding
import com.bumptech.glide.Glide

class SnapFoodResultAdapter(private val list: List<SnapFoodItem>) :
    RecyclerView.Adapter<SnapFoodResultAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemFoodBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val food = list[position]
        holder.bind(food)
    }

    override fun getItemCount() = list.size

    class ViewHolder(val binding: ItemFoodBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(itemFood: SnapFoodItem) {
            binding.tvRecipe.text = itemFood.food
            binding.tvProbability.text = String.format(
                itemView.context.getString(R.string.probability),
                itemFood.probability
            )

            Glide.with(itemView)
                .load(itemFood.imageLink)
                .into(binding.imgRecipe)
        }
    }
}