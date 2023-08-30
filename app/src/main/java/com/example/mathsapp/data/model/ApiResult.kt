package com.example.mathsapp.data.model

/** Sealed class to pass the response to the presentation layer. */
sealed class ApiResult {
    data class Success(val result: String): ApiResult()
    data class Error(val error: String): ApiResult()
}
