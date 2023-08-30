package com.example.mathsapp.di

import android.app.Application
import androidx.room.Room
import com.example.mathsapp.data.local.ResultsDao
import com.example.mathsapp.data.local.ResultsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDataBase(application: Application) : ResultsDatabase {
        return Room
            .databaseBuilder(application, ResultsDatabase::class.java, "results_database")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideDao(database: ResultsDatabase): ResultsDao {
        return database.getDao()
    }
}
