package com.jxhem.uicentric.ui.navigation

import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.jxhem.uicentric.R
import com.jxhem.uicentric.ui.views.detailview.DetailView
import com.jxhem.uicentric.ui.views.mainview.MainView


class NavigationController(private val activityContext: AppCompatActivity) {
    private val containerId: Int = R.id.container
    private val fragmentManager: FragmentManager = activityContext.supportFragmentManager

    fun navigateToDetailView(id: Long) {
        var detailView = DetailView.create(id)
        val tag = "comments/$id"
        fragmentManager.beginTransaction()
                .replace(containerId, detailView, tag)
                .addToBackStack(null)
                .commitAllowingStateLoss()
    }

    fun navigateToMainView() {
        val mainView = MainView.create()

        fragmentManager.beginTransaction()
                .replace(containerId, mainView)
                .commitAllowingStateLoss()

    }

    fun findFragment(id: String): Fragment? {
        return fragmentManager.findFragmentByTag(id)
    }

    fun startApp(data: Uri?, savedInstanceState: Bundle?) {
        if (data != null) {
            val pathSegments = data.pathSegments
            try {
                if (pathSegments.size > 0) {
                    val path = data.getPath()
                    val idStr = path.substring(path.lastIndexOf('/') + 1)
                    val id = Integer.parseInt(idStr)
                    navigateToDetailView(id.toLong())
                }
            } catch (ex: NumberFormatException) {
                navigateToMainView()
                Toast.makeText(activityContext, "No id fonund", Toast.LENGTH_LONG).show()
            }
        } else if (savedInstanceState == null) {
            navigateToMainView()
        }
    }
}