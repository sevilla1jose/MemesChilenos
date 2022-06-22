package com.jsevilla.memeschilenos.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "memes_chile_table")
data class MemeLocalEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "url") var url: String,
    @ColumnInfo(name = "score") var score: Int,
    @ColumnInfo(name = "num_comments") var numComments: Int
)
