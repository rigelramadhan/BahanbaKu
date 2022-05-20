package com.bangkit.bahanbaku.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.bahanbaku.data.remote.response.RecipeEntity
import com.bangkit.bahanbaku.databinding.ItemRecipeSearchBinding
import com.bangkit.bahanbaku.ui.detail.DetailActivity

class SearchRecipeAdapter(private val recipes: List<RecipeEntity>) :
    RecyclerView.Adapter<SearchRecipeAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemRecipeSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val recipe = recipes[position]
        holder.bind(recipe)

        holder.binding.cardRecipe.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_RECIPE_ID, recipe.id)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount() = recipes.size

    inner class ViewHolder(val binding: ItemRecipeSearchBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(recipe: RecipeEntity) {
            binding.tvRecipe.text = recipe.title
            binding.tvRecipeDescription.text = recipe.description
        }
    }
}