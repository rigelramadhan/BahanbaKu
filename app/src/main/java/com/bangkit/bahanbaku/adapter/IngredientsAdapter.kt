package com.bangkit.bahanbaku.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.bahanbaku.data.remote.response.SuppliersItem
import com.bangkit.bahanbaku.databinding.ItemEcommBinding

class IngredientsAdapter(private val list: List<SuppliersItem>) :
    RecyclerView.Adapter<IngredientsAdapter.ViewHolder>() {
    class ViewHolder(val binding: ItemEcommBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SuppliersItem) {
            binding.tvStoreName.text = item.supplierName
            binding.tvShippingCost.text = "Rp.${item.shippingCost}"

            binding.rvIngredients.apply {
                adapter = IngredientsItemAdapter(item.products)
                layoutManager = LinearLayoutManager(this.context)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemEcommBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val supplier = list[position]
        holder.bind(supplier)
    }

    override fun getItemCount() = list.size
}