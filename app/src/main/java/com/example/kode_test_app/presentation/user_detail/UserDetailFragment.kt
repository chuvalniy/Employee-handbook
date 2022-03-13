package com.example.kode_test_app.presentation.user_detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kode_test_app.BaseFragment
import com.example.kode_test_app.R
import com.example.kode_test_app.databinding.FragmentUserDetailBinding


class UserDetailFragment : BaseFragment<FragmentUserDetailBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentUserDetailBinding.inflate(inflater, container, false)

}