package com.example.checklist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.checklist.databinding.FragmentSummaryPageBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class SummaryPage : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentSummaryPageBinding? = null
    private val binding get() = _binding!!
    private lateinit var SummaryItemAdapter: SummaryItemAdapter
    private lateinit var Summarylist: ArrayList<SummaryItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSummaryPageBinding.inflate(inflater,container,false)

        //サンプルデータ
        val a = SummaryItem("タイトル1","1", ">")
        val b = SummaryItem("タイトル2","2", ">")
        val c = SummaryItem("タイトル3","3", ">")
        val d = SummaryItem("タイトル4","4", ">")
        Summarylist = arrayListOf(a,b,c,d)

        _binding!!.summaryRV.layoutManager = LinearLayoutManager(context)
        SummaryItemAdapter = SummaryItemAdapter(Summarylist)
        _binding!!.summaryRV.adapter = SummaryItemAdapter

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SummaryPage().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}