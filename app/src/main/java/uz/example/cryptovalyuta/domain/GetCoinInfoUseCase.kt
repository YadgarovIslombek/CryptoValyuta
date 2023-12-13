package uz.example.cryptovalyuta.domain

import javax.inject.Inject

class GetCoinInfoUseCase @Inject constructor (private val repository: CoinRepository) {
    operator fun invoke(fromSymbol: String) = repository.getCoin(fromSymbol)
}