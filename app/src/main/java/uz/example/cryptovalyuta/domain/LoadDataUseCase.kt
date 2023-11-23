package uz.example.cryptovalyuta.domain

class LoadDataUseCase(val repository: CoinRepository) {
 operator fun invoke() = repository.loadData()
}