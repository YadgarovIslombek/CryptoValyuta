package uz.example.cryptovalyuta.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import uz.example.cryptovalyuta.data.model.CoinInfoDto

@Database(entities = [CoinInfoDbModel::class], version = 2, exportSchema = false)
abstract class AppDatabase:RoomDatabase()  {

    companion object{
        private var db: AppDatabase?= null
        private const val DB_NAME="main.db"

        private val LOCK = Any()

        fun getInstens(context: Context): AppDatabase {
            synchronized(LOCK){
                db?.let { return it }
                val instans = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    DB_NAME
                ).fallbackToDestructiveMigration()
                    .build()
                db =instans
                return instans
            }
        }
    }
    abstract fun coinPriceInfoDao(): CoinPriceInfoDao


}