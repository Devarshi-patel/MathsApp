package com.example.mathsapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "expr_results_table")
data class ResultExpr(
    val value: String,
    val dateAndTime: Long,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)
