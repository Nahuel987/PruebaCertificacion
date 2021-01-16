package com.example.pruebacertificacion.modelo

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import com.example.pruebacertificacion.bd.BooksRoomDatabase
import com.example.pruebacertificacion.pojo.Books
import com.example.pruebacertificacion.remoto.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class BooksRepositorio(context: Context) {

    private val tag = "BooksRepositorio"

    private val db: BooksRoomDatabase = BooksRoomDatabase.getDatabase(context)
    private val booksList = db.booksDao().getAllListBooks()

    fun passLiveDataToViewModel(): LiveData<List<Books>> {
        return booksList
    }

    fun fetchDataFromServer() {
        val service = RetrofitClient.retrofitInstance()
        val call = service.getAllBooks()

        call.enqueue(object : Callback<List<Books>> {
            override fun onResponse(call: Call<List<Books>>, response: Response<List<Books>>) {
                Log.d(tag, response.body().toString())
                CoroutineScope(Dispatchers.IO).launch {
                    response.body()?.let { db.booksDao().insertAllBooks(it) }
                }
            }

            override fun onFailure(call: Call<List<Books>>, t: Throwable) {
                Log.d(tag, t.message.toString())
            }
        })

    }


}