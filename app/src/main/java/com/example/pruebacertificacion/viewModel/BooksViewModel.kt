package com.example.pruebacertificacion.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.pruebacertificacion.modelo.BooksRepositorio
import com.example.pruebacertificacion.pojo.Books


class BooksViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = BooksRepositorio(application)
    private val booksList = repository.passLiveDataToViewModel()

    fun fetchFromServer() {
        repository.fetchDataFromServer()
    }

    fun getDataFromDB(): LiveData<List<Books>> {
        return booksList
    }

}