package com.example.pruebacertificacion.bd

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pruebacertificacion.pojo.Books


@Dao
interface BooksDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllBooks(listBooks: List<Books>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBooks(books: Books)

    @Query("SELECT * FROM books_table ORDER BY id ASC")
    fun getAllListBooks(): LiveData<List<Books>>

    @Query("DELETE FROM books_table")
    suspend fun deleteAllBooks()
}