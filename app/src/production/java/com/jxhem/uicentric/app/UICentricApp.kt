package com.jxhem.uicentric.app

import android.app.Application
import android.content.Context
import com.jxhem.uicentric.datalayer.DataModule
import com.jxhem.uicentric.datalayer.repository.DataRepository
import com.jxhem.uicentric.datalayer.repository.NetworkRepository

class UICentricApp : Application(), ServiceLocator {

    private val dataModule: DataModule = DataModule()
    private val appExecutors = AppExecutors.getInstance()

    override fun provideContext(): Context {
        return this
    }

    override fun provideDataRepository(): DataRepository {
        return NetworkRepository(dataModule.provideDataService(), appExecutors)
    }

}
