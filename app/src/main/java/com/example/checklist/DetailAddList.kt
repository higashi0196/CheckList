package com.example.checklist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.checklist.databinding.FragmentDetailAddListBinding

class DetailAddList : Fragment() {
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

        val detailitem = binding.deitailaddtitle.getText().toString()
        binding.detailaddbtn.setOnClickListener {

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