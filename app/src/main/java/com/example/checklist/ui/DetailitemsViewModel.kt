package com.example.checklist.ui

import androidx.lifecycle.ViewModel

class DetailitemsViewModel: ViewModel() {
    val detailitems = mutableListOf<String>()
    //val detailitems = mutableMapOf<String, String>()

    fun itemsData(items: String){
        detailitems.add(items)
        //detailitems.put(dbname)
    }

}