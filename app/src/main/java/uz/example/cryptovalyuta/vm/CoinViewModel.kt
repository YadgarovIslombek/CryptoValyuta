package uz.example.cryptovalyuta.vm

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import uz.example.cryptovalyuta.database.AppDatabase
import uz.example.cryptovalyuta.network.ApiClient
import uz.example.cryptovalyuta.network.ApiService
import uz.example.cryptovalyuta.pojos.CoinPriceInfo
import uz.example.cryptovalyuta.pojos.CoinPriceInfoRowData

class CoinViewModel(application: Application):AndroidViewModel(application) {
    private val apiService=ApiClient.getRetrofit().create(ApiService::class.java)
    private val scope= CoroutineScope(Dispatchers.IO)
    private val db=AppDatabase.getInstens(application).coinPriceInfoDao()
    private val TAG = "CoinViewModel"
    val getPriceList = db.getPriceList()
    init {
        load()
    }
    fun getDetailInfo(fsyms :String):LiveData<CoinPriceInfo>{
        return db.getPriceCoinInfoAbout(fsyms)
    }
    private fun load() {
        scope.launch {
            val topCoinsInfo = withContext(Dispatchers.IO) { apiService.getTopCoinsInfo() }
            val coins = topCoinsInfo.data?.map { it.coinInfo?.name }?.joinToString(",")
            val coinPriceInfoRowData =
                withContext(Dispatchers.IO) { apiService.getFullInformation(fsym = coins.toString()) }
            val priceListFromRowData = getPriceListFromRowData(coinPriceInfoRowData)
            db.inserPriceList(priceListFromRowData)
        }
    }
    private fun getPriceListFromRowData(coinPriceInfoRowData: CoinPriceInfoRowData): List<CoinPriceInfo> {
        val result = ArrayList<CoinPriceInfo>()
        val jsonObject = coinPriceInfoRowData.coinPriceInfoJsonObject ?: return result
        val keySet = jsonObject.keySet()
        for (key in keySet) {
            val currentJSon = jsonObject.getAsJsonObject(key)
            val keySetCurrent = currentJSon.keySet()
            for (currentKey in keySetCurrent) {
                val priceInfo = Gson().fromJson(currentJSon.getAsJsonObject(currentKey), CoinPriceInfo::class.java)
                result.add(priceInfo)
            }

        }
        return result

    }


    override fun onCleared() {
        scope.cancel()
        super.onCleared()
    }
}