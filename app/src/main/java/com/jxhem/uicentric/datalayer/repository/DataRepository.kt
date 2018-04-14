package com.jxhem.uicentric.datalayer.repository

import android.arch.lifecycle.LiveData
import com.jxhem.uicentric.datalayer.net.Model
import com.jxhem.uicentric.datalayer.net.Resource

interface DataRepository {

    fun getComments(): LiveData<Resource<List<Model.Comment>>>
    fun getComment(id: Long): LiveData<Resource<Model.Comment>>
}