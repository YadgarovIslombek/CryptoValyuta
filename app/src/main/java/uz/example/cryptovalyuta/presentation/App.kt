package uz.example.cryptovalyuta.presentation

import android.app.Application
import androidx.work.Configuration
import com.mocklets.pluto.Pluto
import uz.example.cryptovalyuta.data.database.AppDatabase
import uz.example.cryptovalyuta.data.mapper.CoinMapper
import uz.example.cryptovalyuta.data.network.ApiClient
import uz.example.cryptovalyuta.data.network.ApiService
import uz.example.cryptovalyuta.data.workers.MyRefreshWorkerFactory
import uz.example.cryptovalyuta.di.DaggerApplicationComponent
import javax.inject.Inject

class App : Application(), Configuration.Provider {

    @Inject
    lateinit var myRefreshWorkerFactory:MyRefreshWorkerFactory


    val component by lazy {
        DaggerApplicationComponent.factory()
            .create(this)

    }
    override fun onCreate() {
        component.inject(this)
        Pluto.initialize(this)
        super.onCreate()
    }
    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder()
            .setWorkerFactory(
                myRefreshWorkerFactory
            ).build()
    }


}