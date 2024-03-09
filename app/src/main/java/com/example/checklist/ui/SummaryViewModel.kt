package com.example.checklist.ui

import androidx.lifecycle.ViewModel

class SummaryViewModel: ViewModel() {
    val summarydata = mutableListOf<String>()

    fun addData(data: String){
        summarydata.add(data)
    }

}