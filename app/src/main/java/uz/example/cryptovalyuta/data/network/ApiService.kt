package uz.example.cryptovalyuta.data.network

import retrofit2.http.GET
import retrofit2.http.Query
import uz.example.cryptovalyuta.data.model.CoinNamesListDto
import uz.example.cryptovalyuta.data.model.CoinInfoJsonContainerDto

interface ApiService {

    @GET("top/totalvolfull")
    suspend fun getTopCoinsInfo(
        @Query(API_KEY)apiKey:String="",
        @Query(LIMIT)limit:Int = 50,
        @Query(TSYM)tsym:String = CURRENCY,
        ): CoinNamesListDto

    @GET("pricemultifull")
    suspend fun getFullInformation(
        @Query(API_KEY)apiKey:String = "",
        @Query(FSYM)fsym:String,
        @Query(TOSYM)tsyms:String = CURRENCY,
    ): CoinInfoJsonContainerDto


    companion object{
        private const val API_KEY = "api_key"
        private const val LIMIT = "limit"
        private const val TSYM = "tsym"
        private const val TOSYM = "tsyms"
        private const val FSYM = "fsyms"
        private const val CURRENCY = "USD"


    }
}