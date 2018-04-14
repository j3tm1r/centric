package com.jxhem.uicentric.app

import android.app.Application
import android.content.Context
import com.jxhem.uicentric.datalayer.repository.DataRepository
import com.jxhem.uicentric.datalayer.repository.MockRepository

class UICentricApp : Application(), ServiceLocator {

    private val appExecutors = AppExecutors.getInstance()

    override fun provideContext(): Context {
        return this
    }

    override fun provideDataRepository(): DataRepository {
        return MockRepository(provideContext(), appExecutors)
    }
}
