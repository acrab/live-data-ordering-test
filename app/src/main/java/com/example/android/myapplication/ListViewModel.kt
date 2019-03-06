package com.example.android.myapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

@OpenForTesting
class ListViewModel : ViewModel() {

    val listData : LiveData<List<ListItem>>

    init{
        val x = MutableLiveData<List<ListItem>>()
        listData = x
        x.value = listOf(ListItem("A"), ListItem("B"), ListItem("C"))
    }
}

data class ListItem(val data:String)