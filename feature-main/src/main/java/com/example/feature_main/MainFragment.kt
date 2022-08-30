package com.example.feature_main

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.core.ui.BaseFragment
import com.example.feature_main.databinding.FragmentMainBinding

class MainFragment : BaseFragment<FragmentMainBinding>() {

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentMainBinding.inflate(inflater, container, false)
}