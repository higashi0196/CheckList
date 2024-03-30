package com.example.checklist.ui

import androidx.lifecycle.ViewModel

class DetailitemsViewModel: ViewModel() {
    val detailitems = mutableMapOf<String,String>()

    fun saveData(items: Map<String, String>){
        detailitems.clear()
        detailitems.putAll(items)
    }

    fun getDetaiData(): MutableMap<String, String> {
        return detailitems
        //return detailitems["title"]
    }

}