package com.example.mathsapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mathsapp.data.model.ResultExpr

@Database(entities = [ResultExpr::class], version = 1, exportSchema = false)
abstract class ResultsDatabase: RoomDatabase() {

    abstract fun getDao(): ResultsDao
}