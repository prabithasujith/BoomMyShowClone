package com.prabitha.kotlin.boommyshowclone.dataremote.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "table_data")
class MovieResponse(

    @PrimaryKey
    val page: Int = 1,

    @ColumnInfo(name = "response")
    val results: List<Movie>
)