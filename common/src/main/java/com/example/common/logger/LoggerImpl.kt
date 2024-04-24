package com.example.common.logger

import com.example.common.BuildConfig
import timber.log.Timber

internal class LoggerImpl : Logger {
    init {
        if (BuildConfig.DEBUG && Timber.treeCount == 0) {
            Timber.plant(Timber.DebugTree())
        }
    }

    override fun d(message: String) {
        Timber.d(message)
    }

    override fun i(message: String) {
        Timber.i(message)
    }

    override fun w(tag: String, message: String) {
        Timber.tag(tag).w(message)
    }

    override fun w(message: String) {
        Timber.w(message)
    }

    override fun e(message: String) {
        Timber.e(message)
    }

    override fun e(throwable: Throwable) {
        Timber.e(throwable)
    }
}
