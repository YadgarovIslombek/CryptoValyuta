package uz.example.cryptovalyuta.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import uz.example.cryptovalyuta.data.model.CoinInfoDto

@Dao
interface CoinPriceInfoDao {
    @Query("SELECT * FROM full_price_list ORDER by lastupdate DESC")
    fun getPriceList():LiveData<List<CoinInfoDbModel>>

    @Query("Select  * from full_price_list where fromSymbol = :fsym Limit 1")
    fun getPriceCoinInfoAbout(fsym:String):LiveData<CoinInfoDbModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     suspend fun inserPriceList(priceList:List<CoinInfoDbModel>)

}