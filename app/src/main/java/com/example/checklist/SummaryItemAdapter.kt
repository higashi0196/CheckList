package com.example.checklist

import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.checklist.databinding.SummaryrecyclerviewBinding

class SummaryItemAdapter(val summarylist: ArrayList<SummaryItem>): RecyclerView.Adapter<SummaryItemAdapter.ViewHolder>() {

    class ViewHolder(val binding: SummaryrecyclerviewBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = SummaryrecyclerviewBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewholder: ViewHolder, position: Int) {
        val pos = summarylist[position]
        viewholder.binding.SummaryTitle.text = pos.title
        viewholder.binding.SummaryNumber.text = pos.number
        viewholder.binding.SummaryArrow.text = pos.arrow
    }

    override fun getItemCount(): Int = summarylist.size

}
