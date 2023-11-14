package uz.example.cryptovalyuta.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import uz.example.cryptovalyuta.pojos.CoinPriceInfo

@Dao
interface CoinPriceInfoDao {
    @Query("SELECT * FROM full_price_list ORDER by lastupdate DESC")
    fun getPriceList():LiveData<List<CoinPriceInfo>>

    @Query("Select  * from full_price_list where fromSymbol = :fsym Limit 1")
    fun getPriceCoinInfoAbout(fsym:String):LiveData<CoinPriceInfo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun inserPriceList(priceList:List<CoinPriceInfo>)

}