package com.denet.data.di

import android.content.Context
import androidx.room.Room
import com.denet.data.local.dao.NodeDao
import com.denet.data.local.db.AppDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private const val DATABASE_NAME = "Room_Node_App_DataBase"

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDataBase =
        Room.databaseBuilder(context, AppDataBase::class.java, DATABASE_NAME).build()

    @Provides
    fun provideNodeDao(db: AppDataBase): NodeDao = db.nodeDao()
}