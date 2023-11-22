package uz.example.cryptovalyuta.domain

class GetCoinInfoUseCase(private val repository: CoinRepository) {
    operator fun invoke(fromSymbol: String) = repository.getCoin(fromSymbol)
}