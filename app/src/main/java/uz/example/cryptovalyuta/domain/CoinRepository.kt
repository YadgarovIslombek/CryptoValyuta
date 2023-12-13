package uz.example.cryptovalyuta.domain

import androidx.lifecycle.LiveData
import java.text.DateFormatSymbols

interface CoinRepository {
    fun getCoinInfoList():LiveData<List<CoinInfo>>
    fun getCoin(fromSymbol: String):LiveData<CoinInfo>

     fun loadData()
}