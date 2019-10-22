package com.example.demo.user

data class Response<T>(val code: Int, val error: String? = null, val message: String, val data: T?)