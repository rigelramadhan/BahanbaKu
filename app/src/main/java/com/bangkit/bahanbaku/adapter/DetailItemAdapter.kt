package com.bangkit.bahanbaku.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.bahanbaku.databinding.ItemDetailListBinding
import com.bangkit.bahanbaku.databinding.ItemIngredientsBinding

class DetailItemAdapter(private val list: List<String>) : RecyclerView.Adapter<DetailItemAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemDetailListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ingredient = list[position]
        holder.bind(ingredient)
    }

    override fun getItemCount() = list.size

    inner class ViewHolder(val binding: ItemDetailListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(ingredient: String?) {
            binding.tvIngredient.text = ingredient
        }
    }
}