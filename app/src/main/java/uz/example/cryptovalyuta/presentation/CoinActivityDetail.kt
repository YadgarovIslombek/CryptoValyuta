package uz.example.cryptovalyuta.presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import uz.example.cryptovalyuta.R
import uz.example.cryptovalyuta.data.network.ApiClient.IMG
import uz.example.cryptovalyuta.databinding.ActivityCoinDetailBinding
import uz.example.cryptovalyuta.presentation.vm.CoinViewModel
import uz.example.cryptovalyuta.presentation.vm.CoinViewModel_Factory
import uz.example.cryptovalyuta.presentation.vm.ViewModelFactory
import uz.example.cryptovalyuta.util.convertTimeCustom
import javax.inject.Inject

class CoinActivityDetail : AppCompatActivity() {
    private lateinit var binding:ActivityCoinDetailBinding
    private lateinit var coinViewModel: CoinViewModel
    @Inject
     lateinit var coinviewmodelFactory: ViewModelFactory

    private val component by lazy {
        (application as App).component
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityCoinDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        coinViewModel = ViewModelProvider(this,coinviewmodelFactory)[CoinViewModel::class.java]
        val key = intent.getStringExtra(KEY)?:""
        coinViewModel.getDetailInfo(key).observe(this, Observer {
            binding.tvPrice.text = it.price.toString()
            binding.tvMinPrice.text = it.lowDay.toString()
            binding.tvMaxPrice.text = it.highday.toString()
            binding.tvLastMarket.text = it.lastMarket.toString()
            binding.tvLastUpdate.text = convertTimeCustom(it.lastUpdate)
            binding.tvFromSymbol.text = it.fromSymbol.toString()
            binding.tvToSymbol.text = it.toSymbol.toString()
            Picasso.get().load(IMG + it.imageUrl).into(binding.ivLogoCoin)
        })
    }
    companion object {
        const val KEY = "key"
        fun newIntent(context: Context, key:String): Intent {
            return Intent(context,CoinActivityDetail::class.java).apply {
                putExtra(KEY,key)
            }
        }
    }
}