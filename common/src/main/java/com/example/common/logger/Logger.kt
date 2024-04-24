package com.example.common.logger

interface Logger {
    fun d(message: String)
    fun i(message: String)
    fun w(tag: String, message: String)
    fun w(message: String)
    fun e(message: String)
    fun e(throwable: Throwable)
}

const val TAG_OKHTTP = "OkHttp"
