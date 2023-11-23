package uz.example.cryptovalyuta.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName
import org.jetbrains.annotations.NotNull
import uz.example.cryptovalyuta.data.network.ApiClient.IMG
import uz.example.cryptovalyuta.util.convertTimeCustom

@Entity(tableName = "full_price_list")
data class CoinInfoDbModel(
    @PrimaryKey
    val fromSymbol: String,
    val toSymbol: String?,
    val price: Double?,
    val lastUpdate: Long?,
    val highday: Double?,
    val lowDay:Double?,
    val lastMarket:String?,
    val imageUrl:String?)