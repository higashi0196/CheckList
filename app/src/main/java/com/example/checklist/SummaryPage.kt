package com.example.checklist

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSummaryPageBinding.inflate(inflater,container,false)

        binding.mainaddbtn.setOnClickListener {
            parentFragmentManager.beginTransaction().apply {
                replace(R.id.frameLayout,SummaryAddList())
                addToBackStack(null)
                commit()
            }
        }

        val dbtitle = summaryViewModel.summarydata

        val adapter = SummaryItemAdapter(dbtitle)
        binding.summaryRV.setHasFixedSize(true)
        binding.summaryRV.layoutManager = LinearLayoutManager(context)
        binding.summaryRV.adapter = SummaryItemAdapter(dbtitle)
        binding.summaryRV.adapter = adapter

        //押下したadapterのタイトルを取得して詳細ページに遷移
        adapter.setOnItemClickListener(object : SummaryItemAdapter.OnItemClickListener {
            override fun onItemClickListener(view: View, pos: String) {
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

}