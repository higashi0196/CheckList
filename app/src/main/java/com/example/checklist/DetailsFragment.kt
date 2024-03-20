package com.example.checklist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import com.example.checklist.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)

        binding.detailaddbtn.setOnClickListener {
            parentFragmentManager.beginTransaction().apply {
                replace(R.id.frameLayout,DetailAddList())
                    .addToBackStack(null)
                    .commit()
            }
        }

        binding.rtnbtn.setOnClickListener {
            parentFragmentManager.apply {
                popBackStack()
            }
        }

        return binding.root
    }

//    private fun readData(){
//        val helper = DBOpenHelper(requireContext())
//        val db = helper.readableDatabase
//        val dbdata = arrayOf(DBOpenHelper._ID,DBOpenHelper.TITLE,DBOpenHelper.STATUS)
//
//        val cursor = db.query(
//            DBOpenHelper.TABLE_NAME,
//            dbdata,
//            null,
//            null,
//            null,
//            null,
//            null
//        )
//        val idlists = mutableListOf<Long>()
//        with(cursor) {
//            while (moveToNext()) {
//                val id = getLong(getColumnIndexOrThrow(DBOpenHelper._ID))
//                idlists.add(id)
//            }
//        }
//        cursor.close()
//    }

}