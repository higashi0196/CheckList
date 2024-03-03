package com.example.checklist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.checklist.databinding.FragmentSummaryPageBinding


class SummaryPage : Fragment() {

    private var _binding: FragmentSummaryPageBinding? = null
    private val binding get() = _binding!!
    private lateinit var SummaryItemAdapter: SummaryItemAdapter
    private lateinit var Summarylist: ArrayList<SummaryItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSummaryPageBinding.inflate(inflater,container,false)

        //サンプルデータ
        val a = SummaryItem("タイトル1","1", ">")
        val b = SummaryItem("タイトル2","2", ">")
        val c = SummaryItem("タイトル3","3", ">")
        val d = SummaryItem("タイトル4","4", ">")
        Summarylist = arrayListOf(a,b,c,d)

        binding.mainaddbtn.setOnClickListener {
            parentFragmentManager.beginTransaction().apply {
                replace(R.id.frameLayout,SummaryAddList())
                addToBackStack(null)
                commit()
            }
        }

        binding.summaryRV.layoutManager = LinearLayoutManager(context)
        SummaryItemAdapter = SummaryItemAdapter(Summarylist)
        binding.summaryRV.adapter = SummaryItemAdapter

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) ={

        }
    }
}