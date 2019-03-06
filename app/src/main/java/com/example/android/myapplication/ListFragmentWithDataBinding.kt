package com.example.android.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.myapplication.databinding.FragmentListBinding

class ListFragmentWithDataBinding : Fragment(), IWithViewModelFactory {

    override lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: ListViewModel by viewModels { viewModelFactory }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding = FragmentListBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.dataList.adapter = StringListAdapter(requireContext())
        binding.dataList.layoutManager = LinearLayoutManager(requireContext())

        return binding.root
    }
}