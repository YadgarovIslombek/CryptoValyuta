package uz.example.cryptovalyuta.pojos

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

data class CoinOfListDatum (
    @SerializedName("Data")
    @Expose
    val data: List<Datum>? = null
)