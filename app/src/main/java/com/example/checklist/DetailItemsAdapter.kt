package com.example.checklist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.checklist.databinding.DetailrecyclerviewBinding

class DetailItemsAdapter(private val items: MutableList<String>): RecyclerView.Adapter<DetailItemsAdapter.ViewHolder>() {

    class ViewHolder(val binding: DetailrecyclerviewBinding): RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        val binding = DetailrecyclerviewBinding.inflate(inflate,parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tmp = items[position]
        holder.binding.detailitems.text = tmp
    }

    override fun getItemCount(): Int = items.size

}