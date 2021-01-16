package com.example.pruebacertificacion.bd

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.pruebacertificacion.pojo.Books

@Database(entities = [Books::class], version = 1, exportSchema = false)
abstract class BooksRoomDatabase : RoomDatabase() {

    abstract fun booksDao(): BooksDAO

    companion object {
        @Volatile
        private var INSTANCE: BooksRoomDatabase? = null

        fun getDatabase(context: Context): BooksRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BooksRoomDatabase::class.java,
                    "books_database"
                )
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}