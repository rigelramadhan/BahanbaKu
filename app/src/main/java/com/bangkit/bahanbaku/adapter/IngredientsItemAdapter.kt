package com.bangkit.bahanbaku.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.bahanbaku.data.remote.response.ProductsItem
import com.bangkit.bahanbaku.databinding.ItemIngredientEcommBinding

class IngredientsItemAdapter(private val list: List<ProductsItem>) :
    RecyclerView.Adapter<IngredientsItemAdapter.ViewHolder>() {
    class ViewHolder(val binding: ItemIngredientEcommBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ProductsItem) {
            binding.tvIngredient.text = item.productName
            binding.tvPrice.text = "Rp.${item.productPrice}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemIngredientEcommBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = list[position]
        holder.bind(product)
    }

    override fun getItemCount() = list.size
}