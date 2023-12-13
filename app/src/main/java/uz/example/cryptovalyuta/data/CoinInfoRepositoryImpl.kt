package uz.example.cryptovalyuta.data

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.map
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import androidx.work.Worker
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import retrofit2.create
import uz.example.cryptovalyuta.data.database.AppDatabase
import uz.example.cryptovalyuta.data.database.CoinPriceInfoDao
import uz.example.cryptovalyuta.data.mapper.CoinMapper
import uz.example.cryptovalyuta.data.network.ApiClient
import uz.example.cryptovalyuta.data.network.ApiService
import uz.example.cryptovalyuta.data.workers.MyRefreshWorker
import uz.example.cryptovalyuta.domain.CoinInfo
import uz.example.cryptovalyuta.domain.CoinRepository
import javax.inject.Inject

class CoinInfoRepositoryImpl @Inject constructor(
    private var context: Context,
    private val coinInfoDao: CoinPriceInfoDao,
    private val mapper: CoinMapper,
) : CoinRepository {


    override fun getCoinInfoList(): LiveData<List<CoinInfo>> =
        MediatorLiveData<List<CoinInfo>>().apply {
            addSource(coinInfoDao.getPriceList()) {
                value = mapper.mapListDbModelToEntity(it)
            }
        }


    override fun getCoin(fromSymbol: String): LiveData<CoinInfo> {
        return coinInfoDao.getPriceCoinInfoAbout(fromSymbol).map {
            mapper.mapDbModelToEntity(it)
        }
    }

    override fun loadData() {
        val worker = WorkManager.getInstance(context)
        worker.enqueueUniqueWork(
            MyRefreshWorker.NAME,
            ExistingWorkPolicy.REPLACE,
            MyRefreshWorker.makeRequest()
        )
    }

}