package uz.example.cryptovalyuta.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import uz.example.cryptovalyuta.R
import uz.example.cryptovalyuta.database.AppDatabase
import uz.example.cryptovalyuta.network.ApiClient
import uz.example.cryptovalyuta.network.ApiService
import uz.example.cryptovalyuta.pojos.CoinInfo
import uz.example.cryptovalyuta.pojos.CoinPriceInfo
import uz.example.cryptovalyuta.pojos.CoinPriceInfoRowData
import uz.example.cryptovalyuta.vm.CoinViewModel
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(){
    private val TAG = "MainActivity"
    private lateinit var coinViewModel: CoinViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       coinViewModel= ViewModelProvider(this)[CoinViewModel::class.java]
//        coinViewModel.getPriceList.observe(this, Observer {
//            Log.d(TAG, "onCreate: $it")
//        })
        coinViewModel.getDetailInfo("USDT").observe(this, Observer {
            Log.d(TAG, "onCreate: $it")
        })
    }





}