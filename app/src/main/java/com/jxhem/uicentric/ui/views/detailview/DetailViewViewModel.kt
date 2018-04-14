package com.jxhem.uicentric.ui.views.detailview

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.jxhem.uicentric.datalayer.net.Model
import com.jxhem.uicentric.datalayer.net.Resource
import com.jxhem.uicentric.datalayer.repository.DataRepository

class DetailViewViewModel : ViewModel() {
    private lateinit var dataRepository: DataRepository

    fun setDataRepository(dataRepository: DataRepository) {
        this.dataRepository = dataRepository
    }

    fun getComment(itemId: Long): LiveData<Resource<Model.Comment>> {
        return dataRepository.getComment(itemId)
    }
}