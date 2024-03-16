package com.example.checklist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.checklist.databinding.SummaryrecyclerviewBinding

class SummaryItemAdapter(private val titles: List<String>):
    RecyclerView.Adapter<SummaryItemAdapter.ViewHolder>(){

    private var listener1: OnItemClickListener? = null

    class ViewHolder(val binding: SummaryrecyclerviewBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = SummaryrecyclerviewBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pos = titles[position]
        holder.binding.SummaryTitle.text = pos
        holder.binding.SummaryNumber.text = position.toString()

        holder.binding.root.setOnClickListener {
            listener1?.onItemClickListener(it,pos)
        }
    }

    interface OnItemClickListener{
        fun onItemClickListener(view: View, pos: String)
    }

    fun setOnItemClickListener(listener: OnItemClickListener){
        this.listener1 = listener
    }

    override fun getItemCount(): Int = titles.size

}
