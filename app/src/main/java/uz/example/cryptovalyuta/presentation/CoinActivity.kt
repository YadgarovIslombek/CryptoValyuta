package uz.example.cryptovalyuta.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import uz.example.cryptovalyuta.databinding.ActivityCoinBinding
import uz.example.cryptovalyuta.presentation.vm.CoinViewModel

class CoinActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private lateinit var coinViewModel: CoinViewModel
    private lateinit var adapter: CoinAdapter
    private lateinit var binding: ActivityCoinBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoinBinding.inflate(layoutInflater)
        setContentView(binding.root)
        coinViewModel = ViewModelProvider(this)[CoinViewModel::class.java]
        coinViewModel.getPriceList.observe(this, Observer {
            adapter = CoinAdapter(it) {
                val newIntent = CoinActivityDetail.newIntent(this, it.fromSymbol)
                startActivity(newIntent)

            }
            binding.rec.adapter = adapter
        })

    }


}