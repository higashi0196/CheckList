package com.example.checklist.ui

import androidx.lifecycle.ViewModel

class SummaryViewModel: ViewModel() {
    //val summarydata = mutableListOf<String>()
    var summarydata = ""

    fun addData(data: String){
        //summarydata.add(data)
        summarydata = data
    }

    fun getdbdata(): String{
        return summarydata
    }

}