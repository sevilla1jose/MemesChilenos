package com.jsevilla.memeschilenos.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "department_table")
data class MemesEntity(
    @PrimaryKey var id: String,
    @ColumnInfo(name = "title") var title: String
)
