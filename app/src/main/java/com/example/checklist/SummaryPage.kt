package com.example.checklist

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.checklist.databinding.FragmentSummaryPageBinding
import com.example.checklist.ui.DetailViewModel
import com.example.checklist.ui.SummaryViewModel

class SummaryPage : Fragment() {

    val summaryViewModel: SummaryViewModel by activityViewModels()
    val detailviewmodel: DetailViewModel by activityViewModels()
    private var _binding: FragmentSummaryPageBinding? = null
    private val binding get() = _binding!!
    private var tabledltbtn = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSummaryPageBinding.inflate(inflater,container,false)

        //テーブル名全リスト取得
        val tableNames = getAllTableNames() as MutableList<String>

        binding.mainaddbtn.setOnClickListener {
            parentFragmentManager.beginTransaction().apply {
                replace(R.id.frameLayout,SummaryAddList())
                addToBackStack(null)
                commit()
            }
        }

        val adapter = SummaryItemAdapter(tableNames)
        binding.summaryRV.setHasFixedSize(true)
        binding.summaryRV.layoutManager = LinearLayoutManager(context)
        binding.summaryRV.adapter = SummaryItemAdapter(tableNames)
        binding.summaryRV.adapter = adapter

        //編集ボタン 削除チェック表示イベント
        binding.editbtn.setOnClickListener {
            tabledltbtn = !tabledltbtn
            adapter.setTableBtnVisibility(tabledltbtn)
        }

        //押下したadapterのタイトルを取得して詳細ページに遷移
        adapter.setOnItemClickListener(object : SummaryItemAdapter.OnItemClickListener {
            override fun onItemClickListener(view: View, pos: String) {
                //クリックしたテーブル名を追加
                summaryViewModel.addData(pos)

                //押下したadapterのタイトルをviewmodelに保存
                detailviewmodel.setdata(pos)
                parentFragmentManager
                    .beginTransaction()
                    .replace(R.id.frameLayout,DetailsFragment())
                    .addToBackStack(null)
                    .commit()
            }
        })
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    //テーブル名全リスト取得
    private fun getAllTableNames(): List<String> {
        val dbhelper = DBOpenHelper(requireContext())
        val db = dbhelper.writableDatabase
        val tableNames = mutableListOf<String>()
        val cursor: Cursor? = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null)
        cursor?.use {
            while (it.moveToNext()) {
                val tableName = it.getString(0)
                tableNames.add(tableName)
            }
        }
        cursor?.close()
        return tableNames
    }

}