package com.example.checklist

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.checklist.databinding.FragmentDetailsBinding
import com.example.checklist.ui.DetailViewModel
import com.example.checklist.ui.DetailitemsViewModel

class DetailsFragment : Fragment() {

    val detailviewmodel: DetailViewModel by activityViewModels()
    val detailitemsviewmodel: DetailitemsViewModel by activityViewModels()
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    val detailItems = mutableListOf<String>()

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

        binding.detailaddbtn.setOnClickListener {
            parentFragmentManager.beginTransaction().apply {
                replace(R.id.frameLayout,DetailAddList())
                    .addToBackStack(null)
                    .commit()
            }
        }

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
        val detailadpter = DetailItemsAdapter(de)
        binding.detailRV.setHasFixedSize(true)
        binding.detailRV.layoutManager = LinearLayoutManager(context)
        binding.detailRV.adapter = DetailItemsAdapter(de)
        binding.detailRV.adapter = detailadpter

        detailadpter.setOnDetailItemClickListener(object: DetailItemsAdapter.OnDetailItemClickListener{
            override fun onDetailItemClickListener(view: View,id: Int, pos: String) {

                //押下したadapter 詳細データ(idカラム、titleカラム)をDetailitemsviewmodelに保存
                val detailDataMap = mapOf(
                    "id" to id.toString(),
                    "title" to pos
                )
                detailitemsviewmodel.saveData(detailDataMap)

                parentFragmentManager.beginTransaction().apply {
                    replace(R.id.frameLayout,DetailChange())
                        .addToBackStack(null)
                        .commit()
                }

            }
        })

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}