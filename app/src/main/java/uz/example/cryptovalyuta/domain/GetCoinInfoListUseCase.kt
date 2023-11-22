package uz.example.cryptovalyuta.domain

import androidx.lifecycle.LiveData

class GetCoinInfoListUseCase(private val repository: CoinRepository) {

    operator fun invoke()=repository.getCoinInfoList()
}