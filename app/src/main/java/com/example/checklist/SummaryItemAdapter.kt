package com.example.checklist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.checklist.databinding.SummaryrecyclerviewBinding

class SummaryItemAdapter(
     val titles: MutableList<String>
): RecyclerView.Adapter<SummaryItemAdapter.ViewHolder>() {

    class ViewHolder(val binding: SummaryrecyclerviewBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = SummaryrecyclerviewBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewholder: ViewHolder, position: Int) {
        val pos = titles[position]
        viewholder.binding.SummaryTitle.text = pos
        viewholder.binding.SummaryNumber.text = position.toString()
    }

    override fun getItemCount(): Int = titles.size

}
