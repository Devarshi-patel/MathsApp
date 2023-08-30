package com.example.mathsapp.data.model

sealed class ApiResult {
    data class Success(val result: String): ApiResult()
    data class Error(val error: String): ApiResult()
}
