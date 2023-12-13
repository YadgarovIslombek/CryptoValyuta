package uz.example.cryptovalyuta.data.mapper

import com.google.gson.Gson
import uz.example.cryptovalyuta.data.database.CoinInfoDbModel
import uz.example.cryptovalyuta.data.model.CoinInfoDto
import uz.example.cryptovalyuta.data.model.CoinInfoJsonContainerDto
import uz.example.cryptovalyuta.data.model.CoinNamesListDto
import uz.example.cryptovalyuta.domain.CoinInfo
import javax.inject.Inject

class CoinMapper @Inject constructor() {

    fun coinInfoDtoToDbModel(dto:CoinInfoDto):CoinInfoDbModel{
        return CoinInfoDbModel(
            fromSymbol = dto.fromSymbol,
            toSymbol = dto.tosymbol,
            price  = dto.price,
            lastUpdate  = dto.lastupdate,
            highday   = dto.highday,
            lowDay = dto.lowday,
            lastMarket  = dto.lastmarket,
            imageUrl  = dto.imageurl,
        )
    }
    fun maptoContainerToListCoinInfo(jsonContainerDto: CoinInfoJsonContainerDto):List<CoinInfoDto>{
        val result = ArrayList<CoinInfoDto>()
        val jsonObject = jsonContainerDto.json ?: return result
        val keySet = jsonObject.keySet() //BTC , SOL
        for (key in keySet) {//BTC ....SOL
            val currentJSon = jsonObject.getAsJsonObject(key) //BTC{}
            val keySetCurrent = currentJSon.keySet() //BTC{price,time, from}
            for (currentKey in keySetCurrent) {
                val priceInfo = Gson().fromJson(currentJSon.getAsJsonObject(currentKey), CoinInfoDto::class.java)
                result.add(priceInfo)
            }

        }
        return result
    }

    fun mapListDbModelEntity(namesListDto: CoinNamesListDto):String {
        return namesListDto.names?.map { it.coinNameDto?.name }?.joinToString(",")?: ""
    }

        fun mapDbModelToEntity(dbModel: CoinInfoDbModel):CoinInfo{
            return CoinInfo(
                fromSymbol = dbModel.fromSymbol,
                toSymbol = dbModel.toSymbol,
                price  = dbModel.price,
                lastUpdate  = dbModel.lastUpdate,
                highday   = dbModel.highday,
                lowDay = dbModel.lowDay,
                lastMarket  = dbModel.lastMarket,
                imageUrl  = dbModel.imageUrl,
            )
        }
    fun mapListDbModelToEntity(list: List<CoinInfoDbModel>) = list.map { mapDbModelToEntity(it) }
}