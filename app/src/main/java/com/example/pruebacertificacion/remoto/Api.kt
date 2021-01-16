package com.example.pruebacertificacion.remoto

import com.example.pruebacertificacion.pojo.BookDetail
import com.example.pruebacertificacion.pojo.Books
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {

    @GET("books")
    fun getAllBooks(): Call<List<Books>>

    @GET("bookDetail/{id}")
    fun bookDetail(@Path("id") id: Int): Call<BookDetail>
}

