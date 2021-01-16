package com.example.pruebacertificacion

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.pruebacertificacion.pojo.Books
import com.example.pruebacertificacion.remoto.Api
import com.google.gson.Gson
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import com.google.common.truth.Truth.assertThat
import org.junit.After
import org.junit.Test
import org.junit.Before
import org.junit.Rule
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    lateinit var mMockWebServer: MockWebServer
    lateinit var booksAPI: Api

    @Before
    fun setUp() {
        mMockWebServer = MockWebServer()
        val retrofitClient = Retrofit.Builder()
            .baseUrl(mMockWebServer.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        booksAPI = retrofitClient.create(Api::class.java)
    }

    @After
    fun shutDown() {
        mMockWebServer.shutdown()
    }

    @Test
    fun getProductsStock() = runBlocking {
        val mresultList = listOf<Books>(
            Books(
                10,
                "Samuel Beckett",
                "Republic of Ireland",
                "https://user-images.githubusercontent.com/22780253/103941794-252c8a00-510e-11eb-9881-25d64b9e7472.jpg",
                "French, English",
                "Molloy, Malone Dies, The Unnamable, the trilogy"
            )
        )
        mMockWebServer.enqueue(MockResponse().setBody(Gson().toJson(mresultList)))
        val result = booksAPI.getAllBooks()
        assertThat(result).isNotNull()
    }

}