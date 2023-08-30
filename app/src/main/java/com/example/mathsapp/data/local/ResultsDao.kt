package com.example.mathsapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.mathsapp.data.model.ResultExpr

@Dao
interface ResultsDao {

    @Insert
    fun insert(resultExpr: ResultExpr)

    @Query("SELECT * FROM expr_results_table ORDER BY dateAndTime DESC")
    fun getAllResults(): List<ResultExpr>
}