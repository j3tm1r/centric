package com.jxhem.uicentric.datalayer.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.jxhem.uicentric.app.AppExecutors
import com.jxhem.uicentric.datalayer.net.DataService
import com.jxhem.uicentric.datalayer.net.Model
import com.jxhem.uicentric.datalayer.net.Resource
import java.io.IOException


class NetworkRepository(private val dataService: DataService, private val appExecutors: AppExecutors) : DataRepository {

    private val comments: MutableLiveData<Resource<List<Model.Comment>>> = MutableLiveData()
    private val comment: MutableLiveData<Resource<Model.Comment>> = MutableLiveData()

    override fun getComment(id: Long): LiveData<Resource<Model.Comment>> {
        appExecutors.networkIO().execute {
            try {
                val response = dataService.getComment(id).execute()
                if (response.isSuccessful && response.body() != null) {
                    comment.postValue(Resource.success(response.body()!!))
                } else {
                    comment.postValue(Resource.error("Error loading comment {$id}", null))
                }
            } catch (exception: IOException) {
                comment.postValue(Resource.error("Error loading comment {$id}", null))
            }
        }
        return comment
    }

    override fun getComments(): LiveData<Resource<List<Model.Comment>>> {
        if (comments.value == null) {
            appExecutors.networkIO().execute {
                try {
                    val response = dataService.getComments().execute()
                    if (response.isSuccessful && response.body() != null) {
                        comments.postValue(Resource.success(response.body()!!))
                    } else {
                        comments.postValue(Resource.error("Error loading comments", null))
                    }
                } catch (exception: IOException) {
                    comments.postValue(Resource.error("Error loading comments", null))
                }
            }
        }
        return comments
    }
}