package uz.example.cryptovalyuta.presentation

import androidx.recyclerview.widget.DiffUtil
import uz.example.cryptovalyuta.domain.CoinInfo

class CoinDiffCallback():DiffUtil.ItemCallback<CoinInfo>() {
    override fun areItemsTheSame(oldItem: CoinInfo, newItem: CoinInfo): Boolean {
        return oldItem.fromSymbol == newItem.fromSymbol
    }

    override fun areContentsTheSame(oldItem: CoinInfo, newItem: CoinInfo): Boolean {
        return oldItem == newItem
    }
}