package uz.example.cryptovalyuta.data

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.map
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import retrofit2.create
import uz.example.cryptovalyuta.data.database.AppDatabase
import uz.example.cryptovalyuta.data.mapper.CoinMapper
import uz.example.cryptovalyuta.data.network.ApiClient
import uz.example.cryptovalyuta.data.network.ApiService
import uz.example.cryptovalyuta.domain.CoinInfo
import uz.example.cryptovalyuta.domain.CoinRepository

class CoinInfoRepositoryImpl(application: Application):CoinRepository {
    private val coinInfoDao = AppDatabase.getInstens(application).coinPriceInfoDao()
    private val mapper = CoinMapper()
    private val apiService = ApiClient.getRetrofit().create(ApiService::class.java)

    override fun getCoinInfoList(): LiveData<List<CoinInfo>> = MediatorLiveData<List<CoinInfo>>().apply {
            addSource(coinInfoDao.getPriceList()){
                value = mapper.mapListDbModelToEntity(it)
            }
        }


    override fun getCoin(fromSymbol: String): LiveData<CoinInfo> {
        return coinInfoDao.getPriceCoinInfoAbout(fromSymbol).map {
            mapper.mapDbModelToEntity(it)
        }
    }

    override suspend fun loadData() {
        while (true) {
            try {
               val topCoins  = apiService.getTopCoinsInfo(limit = 50)
                val fSyms = mapper.mapListDbModelEntity(topCoins)
                val jsonContainer = apiService.getFullInformation(fsym=fSyms)
                val coinInfoDtoList = mapper.maptoContainerToListCoinInfo(jsonContainer)
                val dbModelList = coinInfoDtoList.map { mapper.coinInfoDtoToDbModel(it) }
                coinInfoDao.inserPriceList(dbModelList)
            }catch (e:Exception){
                Log.d("TAG", "load: $e")
            }
            delay(5000)
        }
    }
}