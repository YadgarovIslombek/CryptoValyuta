package uz.example.cryptovalyuta.domain

class LoadDataUseCase(private val repository: CoinRepository) {
 operator fun invoke() = repository.loadData()
}