package com.example.mathsapp.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MathsApi {

    @GET("/v4/")
    suspend fun getEvaluatedExpression(@Query("expr") expression: String): Response<String>
}