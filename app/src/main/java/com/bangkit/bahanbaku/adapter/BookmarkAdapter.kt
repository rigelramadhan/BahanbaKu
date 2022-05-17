package com.bangkit.bahanbaku.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.bahanbaku.data.remote.response.RecipeEntity
import com.bangkit.bahanbaku.databinding.ItemBookmarkBinding
import com.bumptech.glide.Glide

class BookmarkAdapter(private val bookmarks: List<RecipeEntity>) :
    RecyclerView.Adapter<BookmarkAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemBookmarkBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val recipe = bookmarks[position]
        holder.bind(recipe)
    }

    override fun getItemCount() = bookmarks.size

    inner class ViewHolder(val binding: ItemBookmarkBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(recipe: RecipeEntity) {
            binding.tvFeaturedRecipe.text = recipe.title
            binding.tvFeaturedRecipeDescription.text = recipe.title

            Glide.with(itemView.context)
                .load(recipe.images)
                .into(binding.imgFeaturedRecipe)
        }
    }
}