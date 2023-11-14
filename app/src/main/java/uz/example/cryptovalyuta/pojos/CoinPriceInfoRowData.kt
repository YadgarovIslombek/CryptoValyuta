package uz.example.cryptovalyuta.pojos

import com.google.gson.JsonObject
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CoinPriceInfoRowData (
    @SerializedName("RAW")
    @Expose
    val coinPriceInfoJsonObject:JsonObject? = null
)