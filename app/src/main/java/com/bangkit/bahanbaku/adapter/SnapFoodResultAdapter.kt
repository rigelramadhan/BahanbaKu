package com.bangkit.bahanbaku.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.bahanbaku.R
import com.bangkit.bahanbaku.data.remote.response.SnapFoodItem
import com.bangkit.bahanbaku.databinding.ItemFoodBinding
import com.bangkit.bahanbaku.ui.snaprecipe.SnapRecipeActivity
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

        holder.binding.cardFood.setOnClickListener {
            val intent = Intent(holder.itemView.context, SnapRecipeActivity::class.java)
            intent.putExtra(SnapRecipeActivity.EXTRA_FOOD_NAME, food.food)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount() = list.size

    class ViewHolder(val binding: ItemFoodBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(itemFood: SnapFoodItem) {
            binding.tvRecipe.text = itemFood.food

            val probability = itemFood.probability.toDouble() * 100
            val probabilityInt = probability.toInt()

            binding.tvProbability.text = itemView.context.getString(R.string.probability).format(
                probabilityInt
            )

            Glide.with(itemView)
                .load(itemFood.imageLink)
                .into(binding.imgRecipe)
        }
    }
}