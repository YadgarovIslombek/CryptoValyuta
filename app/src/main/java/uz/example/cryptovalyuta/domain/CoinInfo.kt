package uz.example.cryptovalyuta.domain

data class CoinInfo(
    val fromSymbol: String?,
    val toSymbol: String?,
    val price: Double?,
    val lastUpdate: Long?,
    val highday: Double?,
    val lowDay:Double?,
    val lastMarket:String?,
    val imageUrl:String?
) {
}