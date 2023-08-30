package com.example.mathsapp.repository

import com.example.mathsapp.data.local.ResultsDao
import com.example.mathsapp.data.model.ApiResult
import com.example.mathsapp.data.model.ResultExpr
import com.example.mathsapp.data.remote.MathsApi
import javax.inject.Inject
import javax.inject.Singleton

/** Repository class for fetching evaluations from network as well as retrieving past evaluations
 * from the data base.
 *
 * @param mathsApi - The Retrofit API for fetching the evaluations.
 * @param resultsDao - The Database access object for the Room database.
 * */
@Singleton
class AppRepository @Inject constructor(
    private val mathsApi: MathsApi, private val resultsDao: ResultsDao
) {

    /** Fetches the evaluated expressions from the API. */
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

    /** Inserts the result expression into Database. */
    suspend fun insertResult(resultExpr: ResultExpr) {
        resultsDao.insert(resultExpr)
    }

    /** Retrieves the list of past result expressions from the database. */
    suspend fun getResults(): List<ResultExpr> {
        return resultsDao.getAllResults()
    }
}