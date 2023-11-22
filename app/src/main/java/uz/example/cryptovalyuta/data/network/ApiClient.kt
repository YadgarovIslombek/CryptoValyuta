package uz.example.cryptovalyuta.data.network

import com.mocklets.pluto.PlutoInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val BASE_URL = "https://min-api.cryptocompare.com/data/"
     const val IMG = "https://cryptocompare.com/"


    fun getRetrofit():Retrofit{
    val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val okhttp = OkHttpClient.Builder()
            .addInterceptor(PlutoInterceptor())
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okhttp)
            .build()
    }
}