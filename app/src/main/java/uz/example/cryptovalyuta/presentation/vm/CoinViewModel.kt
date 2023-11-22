package uz.example.cryptovalyuta.presentation.vm

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import uz.example.cryptovalyuta.data.database.AppDatabase
import uz.example.cryptovalyuta.data.network.ApiClient
import uz.example.cryptovalyuta.data.network.ApiService
import uz.example.cryptovalyuta.data.model.CoinInfoDto
import uz.example.cryptovalyuta.data.model.CoinInfoJsonContainerDto

class CoinViewModel(application: Application):AndroidViewModel(application) {

}