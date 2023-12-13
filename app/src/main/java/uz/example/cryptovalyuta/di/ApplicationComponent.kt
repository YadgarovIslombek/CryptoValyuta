package uz.example.cryptovalyuta.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import uz.example.cryptovalyuta.presentation.App
import uz.example.cryptovalyuta.presentation.CoinActivity
import uz.example.cryptovalyuta.presentation.CoinActivityDetail

@Component(modules = [DataModule::class,ViewModelModule::class])
@ApplicationScope
interface ApplicationComponent {

    fun inject(activity : CoinActivity)
    fun inject(activity : CoinActivityDetail)
    fun inject(app : App)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context):ApplicationComponent
    }
}