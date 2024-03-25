package com.example.checklist

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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)

        //押下したadapterのタイトルを取得
        val tblname = detailviewmodel.getdata()

        //詳細データ　読み取り
        val dbhelper = DBOpenHelper(requireContext())
        val db = dbhelper.readableDatabase
        val cursor = db.query(
            tblname,
            arrayOf("title","status"),
            null,
            null,
            null,
            null,
            null
        )

        cursor.close()
        binding.detailbartitle.text = tblname

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

        //詳細データリスト取得
        val items = detailitemsviewmodel.detailitems
        val detailadpter = DetailItemsAdapter(items)
        binding.detailRV.setHasFixedSize(true)
        binding.detailRV.layoutManager = LinearLayoutManager(context)
        binding.detailRV.adapter = DetailItemsAdapter(items)
        binding.detailRV.adapter = detailadpter

        return binding.root
    }

}