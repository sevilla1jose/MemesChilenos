package com.jsevilla.memeschilenos.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.jsevilla.memeschilenos.data.local.entity.MemeLocalEntity

@Dao
interface MemesDao {
    @Query("SELECT * FROM memes_chile_table")
    fun getAll(): MutableList<MemeLocalEntity>

    @Insert
    fun insertAll(vararg todo: MemeLocalEntity)

    @Query("DELETE FROM memes_chile_table")
    fun deleteAll()
}
