package uz.example.cryptovalyuta.data.network

import com.mocklets.pluto.PlutoInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val BASE_URL = "https://min-api.cryptocompare.com/data/"
    const val IMG = "https://cryptocompare.com/"


    fun okhttp(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

     return  OkHttpClient.Builder()
            .addInterceptor(PlutoInterceptor())
            .build()

    }

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okhttp())
            .build()

    val apiService = retrofit.create(ApiService::class.java)
}