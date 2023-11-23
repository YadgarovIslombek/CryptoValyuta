package uz.example.cryptovalyuta.data.workers

import android.app.Application
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.Worker
import androidx.work.WorkerParameters
import kotlinx.coroutines.delay
import uz.example.cryptovalyuta.data.database.AppDatabase
import uz.example.cryptovalyuta.data.mapper.CoinMapper
import uz.example.cryptovalyuta.data.network.ApiClient
import uz.example.cryptovalyuta.data.network.ApiService
import uz.example.cryptovalyuta.domain.CoinInfo

class MyRefreshWorker(application: Application,workerParameters: WorkerParameters) : CoroutineWorker(application,workerParameters) {
    private val coinInfoDao = AppDatabase.getInstens(application).coinPriceInfoDao()
    private val mapper = CoinMapper()
    private val apiService = ApiClient.getRetrofit().create(ApiService::class.java)

    override suspend fun doWork(): Result {
        while (true) {
            try {
                val topCoins = apiService.getTopCoinsInfo()
                val fSyms = mapper.mapListDbModelEntity(topCoins)
                val jsonContainer = apiService.getFullInformation(fsym = fSyms)
                val coinInfoDtoList = mapper.maptoContainerToListCoinInfo(jsonContainer)
                Log.d("DATA", "json:$coinInfoDtoList ")
                val dbModelList = coinInfoDtoList.map { mapper.coinInfoDtoToDbModel(it) }
                coinInfoDao.inserPriceList(dbModelList)
            } catch (e: Exception) {
                Log.d("TAG", "load: $e")
            }
            delay(10000)

        }

    }

    companion object{
        const val NAME = "MyRefreshWorker"
        fun makeRequest():OneTimeWorkRequest{
            return OneTimeWorkRequestBuilder<MyRefreshWorker>().build()
        }
    }


}