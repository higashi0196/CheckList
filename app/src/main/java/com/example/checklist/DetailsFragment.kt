package com.example.checklist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.checklist.databinding.FragmentDetailsBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class DetailsFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

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
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)


        return binding.root
    }

    private fun readData(){
        val helper = DBOpenHelper(context)
        val db = helper.readableDatabase
        val dbdata = arrayOf(DBOpenHelper._ID,DBOpenHelper.TITLE,DBOpenHelper.STATUS)

        val cursor = db.query(
            DBOpenHelper.TABLE_NAME,
            dbdata,
            null,
            null,
            null,
            null,
            null
        )
        val idlists = mutableListOf<Long>()
        with(cursor) {
            while (moveToNext()) {
                val id = getLong(getColumnIndexOrThrow(DBOpenHelper._ID))
                idlists.add(id)
            }
        }
        cursor.close()
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}