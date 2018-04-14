package com.jxhem.uicentric.app

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor
import java.util.concurrent.Executors

/**
 * Global executor pools for the whole application.
 *
 */
class AppExecutors(private val diskIO: Executor, private val networkIO: Executor, private val mainThread: Executor) {


    fun diskIO(): Executor {
        return diskIO
    }

    fun networkIO(): Executor {
        return networkIO
    }

    fun mainThread(): Executor {
        return mainThread
    }

    private class MainThreadExecutor : Executor {
        private val mainThreadHandler = Handler(Looper.getMainLooper())
        override fun execute(command: Runnable) {
            mainThreadHandler.post(command)
        }
    }

    companion object {
        fun getInstance(): AppExecutors {
            return AppExecutors(
                    Executors.newSingleThreadExecutor(),
                    Executors.newFixedThreadPool(3),
                    MainThreadExecutor()
            )
        }
    }
}