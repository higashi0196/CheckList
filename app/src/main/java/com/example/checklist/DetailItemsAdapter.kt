package com.example.checklist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.RecyclerView
import com.example.checklist.databinding.DetailrecyclerviewBinding

class DetailItemsAdapter(private val items:MutableList<String>, val dbtitle: String): RecyclerView.Adapter<DetailItemsAdapter.ViewHolder>() {

    private var listener: OnDetailItemClickListener? = null
    private var detaildlebtnVisible = false
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
        holder.binding.detaildltbtn.visibility = if (detaildlebtnVisible) View.VISIBLE else View.GONE

        holder.binding.detaildltbtn.setOnCheckedChangeListener { _, isChecked ->
            detailCheckBoxState(position, isChecked)
        }
    }

    interface OnDetailItemClickListener{
        fun onDetailItemClickListener(view: View,id: Int, pos: String)
    }

    //adapterのクリックイベント
    fun setOnDetailItemClickListener(detaillistener: OnDetailItemClickListener){
        this.listener = detaillistener
    }

    fun setDetaildleBtnVisible(isVisible: Boolean) {
        detaildlebtnVisible = isVisible
        notifyDataSetChanged()
    }


    // チェックボックスの状態を管理するリスト
    private val checkedItems = mutableListOf<Boolean>().apply {
        // 初期状態ではすべてのアイテムがチェックされていない状態にする
        repeat(items.size) {
            add(false)
        }
    }

    fun detailCheckBoxState(position: Int, isChecked: Boolean) {
        checkedItems[position] = isChecked
        notifyDataSetChanged() // リストの更新を通知
    }

    // チェックされたアイテムのデータを削除するメソッド
    fun deleteCheckedItems(requireContext: Context) {
        val dbhelper = DBOpenHelper(requireContext)
        val db = dbhelper.writableDatabase
        for ((index, isChecked) in checkedItems.withIndex()) {
            if (isChecked) {
                val pos = items[index] // データモデルから削除するデータの位置を取得する必要があります
                db.delete(dbtitle, "title = ?", arrayOf(pos))
            }
        }
        db.close()
        notifyDataSetChanged() // リストの更新を通知
    }

    override fun getItemCount(): Int = items.size

}

