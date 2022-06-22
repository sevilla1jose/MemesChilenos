package com.jsevilla.memeschilenos.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jsevilla.memeschilenos.data.local.dao.MemesDao
import com.jsevilla.memeschilenos.data.local.entity.MemeLocalEntity

@Database(
    entities = [MemeLocalEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun memesDao(): MemesDao
}