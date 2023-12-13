package uz.example.cryptovalyuta.presentation.vm

import android.app.Application

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel

import uz.example.cryptovalyuta.data.CoinInfoRepositoryImpl


import uz.example.cryptovalyuta.domain.GetCoinInfoListUseCase
import uz.example.cryptovalyuta.domain.GetCoinInfoUseCase
import uz.example.cryptovalyuta.domain.LoadDataUseCase
import javax.inject.Inject

class CoinViewModel @Inject constructor(
    private val getCoinInfoListUseCase: GetCoinInfoListUseCase,
    private val getCoinInfoUseCase: GetCoinInfoUseCase,
    private val loadDataUseCase: LoadDataUseCase
    ) : ViewModel() {


    val coinInfoList = getCoinInfoListUseCase()

    fun getDetailInfo(fSmy: String) = getCoinInfoUseCase(fSmy)

    init {
        loadDataUseCase()
    }


}