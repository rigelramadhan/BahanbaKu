package com.bangkit.bahanbaku.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.bahanbaku.data.remote.response.AboveBelowItem
import com.bangkit.bahanbaku.databinding.ItemIngredientEcommResponseBinding

class EcommAdapter(private val list: List<AboveBelowItem>) :
    RecyclerView.Adapter<EcommAdapter.ViewHolder>() {
    class ViewHolder(val binding: ItemIngredientEcommResponseBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: AboveBelowItem) {
            binding.tvTotalPrice.text = "Rp${item.totalPrice}"
            binding.rvIngredientsEcommResponse.apply {
                adapter = IngredientsAdapter(item.suppliers)
                layoutManager = LinearLayoutManager(this.context)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemIngredientEcommResponseBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
    }

    override fun getItemCount() = list.size
}