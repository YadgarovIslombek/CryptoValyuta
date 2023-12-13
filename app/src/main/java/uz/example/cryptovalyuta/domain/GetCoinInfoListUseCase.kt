package uz.example.cryptovalyuta.domain

import androidx.lifecycle.LiveData
import javax.inject.Inject

class GetCoinInfoListUseCase @Inject constructor (
    private val repository: CoinRepository
) {
    operator fun invoke()=repository.getCoinInfoList()
}