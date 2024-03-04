package com.example.checklist

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
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

        setFragmentResultListener("key") { _, bundle ->
            val title = bundle.getString("key").toString()
            val a = SummaryItem(title,"1", ">")
            Log.d("title",title)

            Summarylist = arrayListOf(a)
            binding.summaryRV.layoutManager = LinearLayoutManager(context)
            SummaryItemAdapter = SummaryItemAdapter(Summarylist)
            binding.summaryRV.adapter = SummaryItemAdapter
        }

        binding.mainaddbtn.setOnClickListener {
            parentFragmentManager.beginTransaction().apply {
                replace(R.id.frameLayout,SummaryAddList())
                addToBackStack(null)
                commit()
            }
        }

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) ={

        }
    }
}