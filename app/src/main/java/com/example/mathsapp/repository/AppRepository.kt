package com.example.mathsapp.repository

import com.example.mathsapp.data.local.ResultsDao
import com.example.mathsapp.data.model.ApiResult
import com.example.mathsapp.data.model.ResultExpr
import com.example.mathsapp.data.remote.MathsApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppRepository @Inject
constructor(
    private val mathsApi: MathsApi,
    private val resultsDao: ResultsDao
) {

    suspend fun fetchEvaluatedExpression(expr: String): ApiResult {
        val response = mathsApi.getEvaluatedExpression(expr)

        if (response.isSuccessful) {
            if (response.body() != null) {
                return ApiResult.Success(response.body()!!)
            }
        } else if (response.errorBody() != null) {
            return ApiResult.Error(response.errorBody()!!.string())
        }

        return ApiResult.Error("Request unsuccessful.")
    }

    suspend fun insertResult(resultExpr: ResultExpr) {
        resultsDao.insert(resultExpr)
    }

    suspend fun getResults(): List<ResultExpr> {
        return resultsDao.getAllResults()
    }
}