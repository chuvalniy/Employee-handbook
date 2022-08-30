package com.example.kode_test_app

import android.net.Uri
import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import com.example.core_navigation.NavCommand
import com.example.core_navigation.NavigationProvider

class MainActivity : AppCompatActivity(), NavigationProvider {

    private val navController: NavController
        get() = findNavController(R.id.nav_host)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setTheme(R.style.Theme_KodeTestApp)

        setContentView(R.layout.activity_main)

        val window: Window = window
        WindowInsetsControllerCompat(window,window.decorView).isAppearanceLightStatusBars = true
    }

    override fun launch(navCommand: NavCommand) {
        when (val target = navCommand.target) {
            is com.example.core_navigation.NavCommands.DeepLink -> openDeepLink(
                url = target.url,
                isModal = target.isModal,
                isSingleTop = target.isSingleTop
            )
            is com.example.core_navigation.NavCommands.Browser -> Unit
        }
    }

    private fun openDeepLink(url: Uri, isModal: Boolean, isSingleTop: Boolean) {
        val navOptions = if (isModal) {
            NavOptions.Builder()
                .setLaunchSingleTop(isSingleTop)
                .setPopUpTo(if (isSingleTop) R.id.app_nav_graph else -1, inclusive = isSingleTop)
                .build()
        } else {
            NavOptions.Builder()
                .setLaunchSingleTop(isSingleTop)
                .setPopUpTo(if (isSingleTop) R.id.app_nav_graph else -1, inclusive = isSingleTop)
                .build()
        }

        navController.navigate(url, navOptions)
    }
}