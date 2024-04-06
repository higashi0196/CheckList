package com.example.checklist

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.checklist.databinding.FragmentDetailsBinding
import com.example.checklist.ui.DetailViewModel
import com.example.checklist.ui.DetailitemsViewModel
import com.google.android.material.internal.ViewOverlayImpl

class DetailsFragment : Fragment() {

    val detailviewmodel: DetailViewModel by activityViewModels()
    val detailitemsviewmodel: DetailitemsViewModel by activityViewModels()
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    val detailItems = mutableListOf<String>()
    private lateinit var detailAdapter: DetailItemsAdapter

    private var radiobtn = false
    private var deletebtn = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @SuppressLint("Range")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)

        //押下したadapterのタイトルを取得
        val tblname = detailviewmodel.getdata()
        binding.detailbartitle.text = tblname

        //詳細データ　読み取り
        val dbhelper = DBOpenHelper(requireContext())
        val db = dbhelper.readableDatabase

        //変更ボタンイベント
        binding.detailaddbtn.setOnClickListener {
            parentFragmentManager.beginTransaction().apply {
                replace(R.id.frameLayout,DetailAddList())
                    .addToBackStack(null)
                    .commit()
            }
        }

        //戻るボタンイベント
        binding.rtnbtn.setOnClickListener {
            parentFragmentManager.beginTransaction().apply {
                replace(R.id.frameLayout,SummaryPage())
                    .addToBackStack(null)
                    .commit()
            }
        }

        val columnname = "title"
        val sql = "SELECT $columnname FROM $tblname"
        val rawcursor = db.rawQuery(sql,null)

        val de = mutableListOf<String>()
        if (rawcursor.moveToFirst()){
            do {
                val value = rawcursor.getString(rawcursor.getColumnIndex(columnname))
                de.add(value)
            }while (rawcursor.moveToNext())

        }
        rawcursor.close()

        //詳細データリスト取得
        val detailAdapter = DetailItemsAdapter(de, tblname) // アダプターを生成しフィールドに代入
        binding.detailRV.setHasFixedSize(true)
        binding.detailRV.layoutManager = LinearLayoutManager(context)
        binding.detailRV.adapter = detailAdapter
        detailAdapter.notifyDataSetChanged()

        //編集ボタン 削除チェック表示イベント
        val deletetitle = "削除"
        val dailogtitle = "メッセージ"
        binding.detaildelete.setOnClickListener {
            detailAdapter.deleteCheckedItems(requireContext())
            showDialog(requireContext(),dailogtitle,deletetitle)
        }

        binding.detaileditbtn.setOnClickListener {
            radiobtn = !radiobtn
            deletebtn = !deletebtn

            detailAdapter.setDetaildleBtnVisible(radiobtn)
            binding.detaildelete.visibility = if (deletebtn) View.VISIBLE else View.INVISIBLE

        }

        detailAdapter.setOnDetailItemClickListener(object: DetailItemsAdapter.OnDetailItemClickListener{
            override fun onDetailItemClickListener(view: View,id: Int, pos: String) {

                val idCursor = db.rawQuery("SELECT _id FROM $tblname WHERE title = ?",arrayOf(pos))
                var itemId: Int? = null
                if (idCursor.moveToFirst()) {
                    itemId = idCursor.getInt(idCursor.getColumnIndex("_id"))
                }
                idCursor.close()

                //押下したadapter 詳細データ(idカラム、titleカラム)をDetailitemsviewmodelに保存
                val detailDataMap = mapOf(
                    "id" to itemId.toString(),
                    "title" to pos
                )
                detailitemsviewmodel.saveData(detailDataMap)

                parentFragmentManager.beginTransaction().apply {
                    replace(R.id.frameLayout,DetailChange())
                        .addToBackStack(null)
                        .commit()
                }
            }
        }
    )
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun showDialog(context: Context,title: String,msg: String){
        val builder = AlertDialog.Builder(context)
        builder.setTitle(title)
        builder.setMessage(msg)
        builder.setPositiveButton("OK"){
            dialog, which ->
            if (::detailAdapter.isInitialized) {
                detailAdapter.deleteCheckedItems(requireContext())
            }
            parentFragmentManager.beginTransaction().apply {
                replace(R.id.frameLayout,DetailsFragment())
                    .addToBackStack(null)
                    .commit()
            }
            dialog.dismiss()
        }
        val dialog = builder.create()
        dialog.show()
    }

}