package com.example.checklist

import android.os.Parcel
import android.os.Parcelable
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.checklist.databinding.SummaryrecyclerviewBinding

class SummaryItemAdapter(private val summarylist: ArrayList<SummaryItem>): RecyclerView.Adapter<SummaryItemAdapter.ViewHolder>() {

    class ViewHolder(val binding: SummaryrecyclerviewBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

}