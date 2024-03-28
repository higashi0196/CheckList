package com.example.checklist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.checklist.databinding.FragmentDetailChangeBinding
import com.example.checklist.ui.DetailitemsViewModel

class DetailChange : Fragment() {

    val detailitemsviewmodel: DetailitemsViewModel by activityViewModels()
    private var _binding: FragmentDetailChangeBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailChangeBinding.inflate(inflater,container,false)

        binding.changebtn.setOnClickListener {

        }

        binding.changertnbtn.setOnClickListener {
            parentFragmentManager.apply {
                popBackStack()
            }
        }

        val data = detailitemsviewmodel.getDetaiData()
        binding.changetitle.setText(data)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}