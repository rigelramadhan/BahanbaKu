package com.bangkit.bahanbaku.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.bahanbaku.data.remote.response.RecipeEntity
import com.bangkit.bahanbaku.databinding.ItemRecipeHomeBinding
import com.bumptech.glide.Glide

class HomeRecipeAdapter(private val list: List<RecipeEntity>) : RecyclerView.Adapter<HomeRecipeAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRecipeHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val recipe = list[position]
        holder.bind(recipe)
    }

    override fun getItemCount() = list.size

    inner class ViewHolder(val binding: ItemRecipeHomeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(recipe: RecipeEntity?) {
            binding.let {
                binding.tvFeaturedRecipe.text = recipe?.name
                binding.tvFeaturedRecipeDescription.text = recipe?.description
                binding.tvServings.text = "${recipe?.servings}"

                Glide.with(itemView.context)
                    .load(recipe?.photoUrl)
                    .into(binding.imgFeaturedRecipe)
            }
        }
    }
}