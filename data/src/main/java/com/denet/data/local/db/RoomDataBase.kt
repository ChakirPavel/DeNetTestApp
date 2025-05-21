package com.denet.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.denet.data.local.converters.Converter
import com.denet.data.local.dao.NodeDao
import com.denet.data.local.entities.NodeEntity


@Database(entities = [NodeEntity::class], version = 1)
@TypeConverters(Converter::class)
abstract class AppDataBase : RoomDatabase() {
    abstract fun nodeDao(): NodeDao
}