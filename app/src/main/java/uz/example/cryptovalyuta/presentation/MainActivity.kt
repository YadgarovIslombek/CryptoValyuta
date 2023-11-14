package uz.example.cryptovalyuta.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import uz.example.cryptovalyuta.R
import uz.example.cryptovalyuta.database.AppDatabase
import uz.example.cryptovalyuta.network.ApiClient
import uz.example.cryptovalyuta.network.ApiService
import uz.example.cryptovalyuta.pojos.CoinInfo
import uz.example.cryptovalyuta.pojos.CoinPriceInfo
import uz.example.cryptovalyuta.pojos.CoinPriceInfoRowData
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {
    lateinit var job: Job
    private val TAG = "MainActivity"
    private lateinit var apiService: ApiService
    private lateinit var db: AppDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        job = Job()
        launch {

            db = AppDatabase.getInstens(this@MainActivity)
            apiService = ApiClient.getRetrofit().create(ApiService::class.java)
            val topCoinsInfo = withContext(Dispatchers.IO) { apiService.getTopCoinsInfo() }
            val coins = topCoinsInfo.data?.map { it.coinInfo?.name }?.joinToString(",")
            val coinPriceInfoRowData =
                withContext(Dispatchers.IO) { apiService.getFullInformation(fsym = coins.toString()) }
            val priceListFromRowData = getPriceListFromRowData(coinPriceInfoRowData)

            withContext(Dispatchers.IO){ db.coinPriceInfoDao().inserPriceList(priceListFromRowData)}
            Log.d(TAG, "DATA: $coinPriceInfoRowData")


        }
    }

    private fun getPriceListFromRowData(coinPriceInfoRowData: CoinPriceInfoRowData): List<CoinPriceInfo> {
        val result = ArrayList<CoinPriceInfo>()
        val jsonObject = coinPriceInfoRowData.coinPriceInfoJsonObject
        if (jsonObject == null) return result
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

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    private val handler = CoroutineExceptionHandler { _, exeption ->
        Log.d(TAG, ":handler $exeption ")
    }

    override fun onDestroy() {
        job.cancel()
        super.onDestroy()
    }
}