package com.jxhem.uicentric.datalayer.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import com.google.gson.Gson
import com.jxhem.uicentric.app.AppExecutors
import com.jxhem.uicentric.datalayer.net.Model
import java.io.IOException
import java.nio.charset.Charset
import com.google.gson.reflect.TypeToken
import com.jxhem.uicentric.datalayer.net.Resource


class MockRepository(private val context: Context, private val appExecutors: AppExecutors) : DataRepository {

    private var comment: MutableLiveData<Resource<Model.Comment>>? = null
    private var comments: MutableLiveData<Resource<List<Model.Comment>>>? = null
    private val gson = Gson()

    override fun getComments(): LiveData<Resource<List<Model.Comment>>> {
        if (comments == null) {
            comments = MutableLiveData()
            appExecutors.diskIO().execute {
                val listType = object : TypeToken<ArrayList<Model.Comment>>() {}.type
                val response: List<Model.Comment> =
                        gson.fromJson(readRespone("comments.json"), listType)

                comments?.postValue(Resource.success(response))
            }
        }
        return comments as MutableLiveData<Resource<List<Model.Comment>>>
    }

    override fun getComment(id: Long): LiveData<Resource<Model.Comment>> {
        if (comment == null) {
            comment = MutableLiveData()
            appExecutors.diskIO().execute {
                val response =
                        gson.fromJson(readRespone("comments/1.json"), Model.Comment::class.java)
                comment?.postValue(Resource.success(response))
            }
        }
        return comment as MutableLiveData<Resource<Model.Comment>>
    }

    private fun readRespone(path: String): String {

        var resource = "mock/" + path
        var json = ""
        try {
            val inputStream = context.assets.open(resource)

            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer, Charset.forName("UTF-8"))
        } catch (ex: IOException) {
            ex.printStackTrace()
            return "{'code' : 'error'}"
        }
        return json
    }
}