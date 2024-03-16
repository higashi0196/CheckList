package com.example.checklist

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResult
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.checklist.databinding.FragmentSummaryPageBinding
import com.example.checklist.ui.SummaryViewModel


class SummaryPage : Fragment() {

    val summaryViewModel: SummaryViewModel by activityViewModels()
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
        Log.d("data",dbtitle.toString())

        val adapter = SummaryItemAdapter(dbtitle)
        binding.summaryRV.setHasFixedSize(true)
        binding.summaryRV.layoutManager = LinearLayoutManager(context)
        binding.summaryRV.adapter = SummaryItemAdapter(dbtitle)
        binding.summaryRV.adapter = adapter

        adapter.setOnItemClickListener(object : SummaryItemAdapter.OnItemClickListener {
            override fun onItemClickListener(view: View, pos: String) {
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