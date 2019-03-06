package com.example.android.myapplication.util

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.lifecycle.ViewModelProvider
import com.example.android.myapplication.IWithViewModelFactory

class FragmentFactoryWithMockViewModelFactory(private val factory:ViewModelProvider.Factory) : FragmentFactory(){
    override fun instantiate(classLoader: ClassLoader, className: String, args: Bundle?): Fragment {
        val fragment = super.instantiate(classLoader, className, args)

        val listFragment = fragment as IWithViewModelFactory
        listFragment.viewModelFactory = factory

        return fragment
    }
}