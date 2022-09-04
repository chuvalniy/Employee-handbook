package com.example.feature_main.fragment

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.core.presentation.BaseFragment
import com.example.core_navigation.navigation.NavCommand
import com.example.core_navigation.navigation.NavCommands
import com.example.core_navigation.navigation.navigate
import com.example.feature.databinding.FragmentErrorBinding

class ErrorFragment : BaseFragment<FragmentErrorBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnTryAgain.setOnClickListener {
            navigate(
                NavCommand(
                    NavCommands.DeepLink(
                        url = Uri.parse("myApp://featureHome"),
                        isModal = true,
                        isSingleTop = true,
                    )
                )
            )
        }
    }

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentErrorBinding.inflate(inflater, container, false)
}