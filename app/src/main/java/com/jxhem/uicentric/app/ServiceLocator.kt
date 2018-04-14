package com.jxhem.uicentric.app

import android.content.Context
import com.jxhem.uicentric.datalayer.repository.DataRepository

interface ServiceLocator {

    fun provideContext(): Context
    fun provideDataRepository(): DataRepository
}
