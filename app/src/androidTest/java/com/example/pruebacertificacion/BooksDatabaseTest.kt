package com.example.pruebacertificacion

import android.content.Context
import androidx.room.Room
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import com.example.pruebacertificacion.bd.BooksDAO
import com.example.pruebacertificacion.bd.BooksRoomDatabase
import com.example.pruebacertificacion.pojo.Books
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Test
import org.junit.Before
import org.junit.Rule


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

class BooksDatabaseTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var booksDao: BooksDAO
    private lateinit var database: BooksRoomDatabase

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, BooksRoomDatabase::class.java).build()
        booksDao = database.booksDao()
    }

    @After
    fun tearDown() {
        database.close()

    }

    @Test
    fun insertBooks_empty() = runBlocking {

        //Given
        val bookList = listOf<Books>()

        //When
        booksDao.insertAllBooks(bookList)

        //Then
        booksDao.getAllListBooks().observeForever {
            assertThat(it).isNotNull()
            assertThat(it).isEmpty()

        }
    }

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertThat("com.example.pruebacertificacion").isEqualTo(appContext.packageName)
    }
}