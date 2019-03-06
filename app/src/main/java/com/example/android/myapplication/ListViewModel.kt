package com.example.android.myapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class ListViewModel : ViewModel() {

    abstract val listData : LiveData<List<ListItem>>
}

data class ListItem(val data:String)