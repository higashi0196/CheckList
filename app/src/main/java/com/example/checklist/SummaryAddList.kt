package com.example.checklist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.checklist.databinding.FragmentSummaryAddListBinding

class SummaryAddList : Fragment() {

    private var _binding: FragmentSummaryAddListBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSummaryAddListBinding.inflate(inflater,container,false)

        val title = binding.dbtitle.getText()

        binding.addbtn.setOnClickListener {
            if (binding.dbtitle.text.isNotEmpty()){
                val DbHelper = DBOpenHelper(requireContext(),title.toString())
                val db = DbHelper.writableDatabase
                db.close()
            }
            parentFragmentManager.apply {
                popBackStack()
            }
        }

        binding.cancelbtn.setOnClickListener {
            parentFragmentManager.apply {
                popBackStack()
            }
        }

        return binding.root
    }

}
