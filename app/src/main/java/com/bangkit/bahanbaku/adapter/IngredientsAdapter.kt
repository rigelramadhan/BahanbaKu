package com.bangkit.bahanbaku.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.bahanbaku.data.remote.response.ResultsItem
import com.bangkit.bahanbaku.databinding.ItemIngredientEcommBinding

class IngredientsAdapter(private val list: List<ResultsItem>) : RecyclerView.Adapter<IngredientsAdapter.ViewHolder>(){
    class ViewHolder(val binding: ItemIngredientEcommBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ResultsItem) {
            item.shippingPrice
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}