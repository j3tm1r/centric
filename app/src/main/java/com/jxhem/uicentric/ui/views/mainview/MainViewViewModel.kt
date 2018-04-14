package com.jxhem.uicentric.ui.views.mainview

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.jxhem.uicentric.datalayer.net.Model
import com.jxhem.uicentric.datalayer.net.Resource
import com.jxhem.uicentric.datalayer.repository.DataRepository

class MainViewViewModel : ViewModel() {

    private lateinit var dataRepository: DataRepository
    private lateinit var comments: LiveData<Resource<List<Model.Comment>>>

    fun setDataRepository(dataRepository: DataRepository) {
        this.dataRepository = dataRepository
        comments = dataRepository.getComments()
    }

    fun getComments(): LiveData<Resource<List<Model.Comment>>>{
        return comments
    }

}

interface ItemListener {
    fun onItemSelected(id: Long)
}
