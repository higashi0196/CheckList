package com.example.checklist

import android.annotation.SuppressLint
import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.checklist.databinding.FragmentDetailChangeBinding
import com.example.checklist.ui.DetailViewModel
import com.example.checklist.ui.DetailitemsViewModel
import com.example.checklist.ui.SummaryViewModel

class DetailChange : Fragment() {

    val summaryViewModel: SummaryViewModel by activityViewModels()
    val detailviewmodel: DetailViewModel by activityViewModels()
    val detailitemsviewmodel: DetailitemsViewModel by activityViewModels()
    private var _binding: FragmentDetailChangeBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @SuppressLint("Range")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailChangeBinding.inflate(inflater,container,false)

        val data = detailitemsviewmodel.getDetaiData()["title"]
        val idnum = detailitemsviewmodel.getDetaiData()["id"].toString()
        //binding.changetitle.setText(data)

        Log.d("テーブル名",summaryViewModel.summarydata.toString())

        //テーブル名取得
        val dbname = summaryViewModel.summarydata.toString()
        //val change = binding.changetitle.text.toString()


        //詳細データ　更新
        binding.changebtn.setOnClickListener {

            val dbhelper = DBOpenHelper(requireContext())
            val db = dbhelper.writableDatabase

            val changetext = binding.changetitle.text.toString()

            val chagevalue = ContentValues().apply {
                put("title",changetext)
            }
            db.update(
                dbname,
                chagevalue,
                "_id = ?",
                arrayOf(idnum)
            )

            parentFragmentManager.beginTransaction().apply {
                replace(R.id.frameLayout,DetailsFragment())
                    .addToBackStack(null)
                    .commit()
            }
        }

        binding.changertnbtn.setOnClickListener {
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