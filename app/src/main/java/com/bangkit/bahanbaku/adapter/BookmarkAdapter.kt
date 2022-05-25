package com.bangkit.bahanbaku.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.bahanbaku.data.remote.response.RecipeEntity
import com.bangkit.bahanbaku.databinding.ItemBookmarkBinding
import com.bangkit.bahanbaku.ui.detail.DetailActivity
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

        holder.binding.cardBookmark.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_RECIPE_ID, recipe.id)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount() = bookmarks.size

    inner class ViewHolder(val binding: ItemBookmarkBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(recipe: RecipeEntity) {
            binding.tvRecipe.text = recipe.title
            binding.tvRecipeDescription.text = recipe.title

            Glide.with(itemView.context)
                .load(recipe.image)
                .into(binding.imgRecipe)
        }
    }
}