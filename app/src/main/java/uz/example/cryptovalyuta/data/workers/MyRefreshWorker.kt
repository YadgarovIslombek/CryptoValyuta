package uz.example.cryptovalyuta.data.workers

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkerParameters

import kotlinx.coroutines.delay
import uz.example.cryptovalyuta.data.database.AppDatabase
import uz.example.cryptovalyuta.data.mapper.CoinMapper
import uz.example.cryptovalyuta.data.network.ApiClient
import uz.example.cryptovalyuta.data.network.ApiService
import uz.example.cryptovalyuta.domain.CoinInfo

class MyRefreshWorker(context: Context, workerParameters: WorkerParameters) :
    CoroutineWorker(context, workerParameters) {
    private val coinInfoDao = AppDatabase.getInstens(context).coinPriceInfoDao()
    private val mapper = CoinMapper()
    private val apiService = ApiClient.getRetrofit().create(ApiService::class.java)

    override suspend fun doWork(): Result {
        while (true) {
            try {
                val topCoins = apiService.getTopCoinsInfo(limit = 50)
                val fSyms = mapper.mapListDbModelEntity(topCoins)
                val jsonContainer = apiService.getFullInformation( fsym = fSyms)
                val coinInfoDtoList = mapper.maptoContainerToListCoinInfo(jsonContainer)
                val dbModelList = coinInfoDtoList.map { mapper.coinInfoDtoToDbModel(it) }
                coinInfoDao.inserPriceList(dbModelList)
            } catch (e: Exception) {
            }
            delay(10000)
        }
    }



    companion object {
        const val NAME = "MyRefreshWorker"
        fun makeRequest(): OneTimeWorkRequest {
            return OneTimeWorkRequestBuilder<MyRefreshWorker>().build()
        }
    }


}