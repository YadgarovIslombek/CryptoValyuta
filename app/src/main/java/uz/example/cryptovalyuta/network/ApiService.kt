package uz.example.cryptovalyuta.network

import retrofit2.http.GET
import retrofit2.http.Query
import uz.example.cryptovalyuta.pojos.CoinInfo
import uz.example.cryptovalyuta.pojos.CoinOfListDatum
import uz.example.cryptovalyuta.pojos.CoinPriceInfoRowData

interface ApiService {

    @GET("top/totalvolfull")
    suspend fun getTopCoinsInfo(
        @Query(API_KEY)apiKey:String="",
        @Query(LIMIT)limit:Int = 10,
        @Query(TSYM)tsym:String = CURRENCY,
        ):CoinOfListDatum

    @GET("pricemultifull")
    suspend fun getFullInformation(
        @Query(API_KEY)apiKey:String = "",
        @Query(FSYM)fsym:String,
        @Query(TOSYM)tsyms:String = CURRENCY,
    ):CoinPriceInfoRowData


    companion object{
        private const val API_KEY = "api_key"
        private const val LIMIT = "limit"
        private const val TSYM = "tsym"
        private const val TOSYM = "tsyms"
        private const val FSYM = "fsyms"
        private const val CURRENCY = "USD"


    }
}