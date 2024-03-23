package com.example.checklist.ui

import androidx.lifecycle.ViewModel

/*
    メインページの押下したadapterのタイトルを取得、保存
    summarypaga:保存
    detailsfragment:取得
    detailaddlistfragmen:取得
*/

class DetailViewModel: ViewModel() {

    var detaildata: String = ""
    fun setdata(title: String){
        detaildata = title
    }

    fun getdata(): String {
        return detaildata
    }

}

