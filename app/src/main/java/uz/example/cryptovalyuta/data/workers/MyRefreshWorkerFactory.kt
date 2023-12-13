package uz.example.cryptovalyuta.data.workers

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import uz.example.cryptovalyuta.data.database.CoinPriceInfoDao
import uz.example.cryptovalyuta.data.mapper.CoinMapper
import uz.example.cryptovalyuta.data.network.ApiService
import javax.inject.Inject

class MyRefreshWorkerFactory @Inject constructor(
    private val coinInfoDao: CoinPriceInfoDao,
    private val apiService: ApiService,
    private val mapper: CoinMapper,
) : WorkerFactory() {
    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters,
    ): ListenableWorker? {
        return MyRefreshWorker(appContext, workerParameters, coinInfoDao, apiService, mapper)
    }
}