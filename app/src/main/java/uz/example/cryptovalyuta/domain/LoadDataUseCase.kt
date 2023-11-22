package uz.example.cryptovalyuta.domain

class LoadDataUseCase(val repository: CoinRepository) {
suspend operator fun invoke() = repository.loadData()
}