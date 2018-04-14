package com.jxhem.uicentric.ui.views.mainview

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jxhem.uicentric.databinding.FragmentMainBinding
import com.jxhem.uicentric.datalayer.net.Status
import com.jxhem.uicentric.ui.ServiceProvider
import com.jxhem.uicentric.ui.navigation.NavigationProvider
import com.jxhem.uicentric.ui.views.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_main.view.*

class MainView : BaseFragment(), ItemListener {

    private lateinit var viewModel: MainViewViewModel
    private lateinit var binding: FragmentMainBinding
    private lateinit var navigationProvider: NavigationProvider

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewViewModel::class.java)
        viewModel.setDataRepository(
                (activity as ServiceProvider).provideServiceLocator().provideDataRepository()
        )
        navigationProvider = activity as NavigationProvider
        binding.root.comments.adapter = CommentsAdapter(this, emptyList())
        viewModel.getComments().observe(this, Observer {
            when (it?.status) {
                Status.SUCCESS -> {
                    (binding.root.comments.adapter as CommentsAdapter).setComments(it.data!!)
                }
                Status.ERROR -> {
                    showMessage(binding.root, it.message!!)
                }
            }
        })

    }

    override fun onItemSelected(id: Long) {
        navigationProvider.provideNavigationController().navigateToDetailView(id)
    }

    companion object {
        fun create(): MainView {
            return MainView()
        }
    }
}