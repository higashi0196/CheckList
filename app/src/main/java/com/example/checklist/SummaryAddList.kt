package com.example.checklist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.checklist.databinding.FragmentSummaryAddListBinding
import com.example.checklist.ui.SummaryViewModel

class SummaryAddList : Fragment() {

    private val summaryViewModel: SummaryViewModel by activityViewModels()

    private var _binding: FragmentSummaryAddListBinding? = null
    private val binding get() = _binding!!

    private lateinit var dbHelper:DBOpenHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSummaryAddListBinding.inflate(inflater,container,false)

        binding.addbtn.setOnClickListener {
            val titleItem = binding.dbtitle.getText().toString()
            dbHelper = DBOpenHelper(requireContext())

            summaryViewModel.addData(titleItem)
            parentFragmentManager.apply {
                popBackStack()
            }

            val dbname = titleItem.trim()
            if (dbname.isNotEmpty()){
                dbHelper.createTable(dbname)
            }
        }

        binding.cancelbtn.setOnClickListener {
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
