package com.jxhem.uicentric.ui.views.detailview

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jxhem.uicentric.databinding.FragmentDetailBinding
import com.jxhem.uicentric.datalayer.net.Status
import com.jxhem.uicentric.ui.ServiceProvider
import com.jxhem.uicentric.ui.views.base.BaseFragment

class DetailView : BaseFragment() {

    private lateinit var viewModel: DetailViewViewModel
    private lateinit var binding: FragmentDetailBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(DetailViewViewModel::class.java)
        viewModel.setDataRepository(
                (activity as ServiceProvider).provideServiceLocator().provideDataRepository()
        )
        val itemId = arguments?.getLong("itemId")
        viewModel.getComment(itemId!!).observe(this, Observer {

            when (it?.status) {
                Status.SUCCESS -> {
                    binding.comment = it.data
                }
                Status.ERROR -> {
                    showMessage(binding.root, it.message!!)
                }
            }
        })
    }

    companion object {
        fun create(id: Long): DetailView {
            val detailView = DetailView()
            val args = Bundle()
            args.putLong("itemId", id)
            detailView.arguments = args
            return detailView
        }
    }
}