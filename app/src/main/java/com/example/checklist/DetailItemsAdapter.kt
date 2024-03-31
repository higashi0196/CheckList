package com.example.checklist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.checklist.databinding.DetailrecyclerviewBinding

class DetailItemsAdapter(private val items:MutableList<String>): RecyclerView.Adapter<DetailItemsAdapter.ViewHolder>() {

    private var listener: OnDetailItemClickListener? = null

    private var isRadioButtonVisible = false
    fun setRadioButtonVisibility(isVisible: Boolean) {
        isRadioButtonVisible = isVisible
        notifyDataSetChanged() // リストの更新を通知
    }

    class ViewHolder(val binding: DetailrecyclerviewBinding): RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        val binding = DetailrecyclerviewBinding.inflate(inflate,parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.detailitems.text = items[position]

        holder.binding.root.setOnClickListener{
            listener?.onDetailItemClickListener(it,position,items[position])
        }

        holder.binding.detaildltbtn.visibility = if (isRadioButtonVisible) View.VISIBLE else View.GONE
    }

    interface OnDetailItemClickListener{
        fun onDetailItemClickListener(view: View,id: Int, pos: String)
    }

    fun setOnDetailItemClickListener(detaillistener: OnDetailItemClickListener){
        this.listener = detaillistener
    }

    override fun getItemCount(): Int = items.size

}

