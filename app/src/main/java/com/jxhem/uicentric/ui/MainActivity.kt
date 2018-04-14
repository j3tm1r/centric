package com.jxhem.uicentric.ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.jxhem.uicentric.R
import com.jxhem.uicentric.app.ServiceLocator
import com.jxhem.uicentric.ui.navigation.NavigationController
import com.jxhem.uicentric.ui.navigation.NavigationProvider

class MainActivity : AppCompatActivity(), NavigationProvider, ServiceProvider {

    override fun provideServiceLocator(): ServiceLocator {
        if (application is ServiceLocator) {
            return application as ServiceLocator
        } else {
            throw RuntimeException(
                    "No ServiceLocator provided"
            )
        }
    }


    private val navigationController = NavigationController(this)

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        navigationController.startApp(intent?.data, null)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigationController.startApp(intent.data, savedInstanceState)
    }

    override fun provideNavigationController(): NavigationController {
        return navigationController
    }

}

interface ServiceProvider {
    fun provideServiceLocator(): ServiceLocator
}
