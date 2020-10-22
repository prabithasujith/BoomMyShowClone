package com.prabitha.kotlin.boommyshowclone.dataremote.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.prabitha.kotlin.boommyshowclone.dataremote.local.entity.MovieResponse

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movieResponse: MovieResponse )

    @Query(value = "select * from table_data")
    fun getMovies(): MovieResponse
}