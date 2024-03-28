package com.example.checklist

import android.content.ContentValues
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.checklist.databinding.FragmentDetailAddListBinding
import com.example.checklist.ui.DetailViewModel
import com.example.checklist.ui.DetailitemsViewModel

class DetailAddList : Fragment() {

    val detailviewmodel: DetailViewModel by activityViewModels()
    val detailitemsviewmodel: DetailitemsViewModel by activityViewModels()
    private var _binding: FragmentDetailAddListBinding? = null
    private val binding get() = _binding!!
    private lateinit var dbhelper: DBOpenHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailAddListBinding.inflate(inflater,container,false)

        val dbhelper = DBOpenHelper(requireContext())
        val db = dbhelper.writableDatabase

        binding.detailaddbtn.setOnClickListener {
            //DB　titleカラム
            val detailitem = binding.deitailaddtitle.getText().toString()
            val statusitem: Boolean = false

            //押下したadapterのタイトルを取得(テーブル名)
            val dbname = detailviewmodel.getdata()

            //詳細データ insert
            val values = ContentValues().apply {
                put("title",detailitem)
                put("status",statusitem.toString())
            }

            db?.insert(dbname,null,values)

            //detailitemsviewmodel.itemsData(values["title"].toString())

            parentFragmentManager.beginTransaction().apply {
                replace(R.id.frameLayout,DetailsFragment())
                    .addToBackStack(null)
                    .commit()
            }
        }

        binding.detailcancelbtn.setOnClickListener {
            parentFragmentManager.apply {
                popBackStack()
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}