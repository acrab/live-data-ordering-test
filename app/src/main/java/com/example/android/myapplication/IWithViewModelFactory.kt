package com.example.android.myapplication

import androidx.lifecycle.ViewModelProvider

interface IWithViewModelFactory
{
    var viewModelFactory: ViewModelProvider.Factory
}