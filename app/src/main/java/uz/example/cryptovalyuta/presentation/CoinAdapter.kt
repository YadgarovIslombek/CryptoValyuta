package uz.example.cryptovalyuta.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import uz.example.cryptovalyuta.databinding.ItemIconBinding
import uz.example.cryptovalyuta.data.model.CoinInfoDto
import uz.example.cryptovalyuta.data.network.ApiClient.IMG
import uz.example.cryptovalyuta.domain.CoinInfo
import uz.example.cryptovalyuta.util.convertTimeCustom

class CoinAdapter() : ListAdapter<CoinInfo,CoinAdapter.VH>(CoinDiffCallback()) {
    var onclick:OnClickItem? = null
    inner class VH(val binding: ItemIconBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemIconBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }


    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.binding.tvSymbols.text = getItem(position).fromSymbol + "/" + getItem(position).toSymbol
        holder.binding.tvPrice.text = getItem(position).price.toString()
        holder.binding.tvLastUpdate.text = "Oxirgi yangilangan vaqti: "+ convertTimeCustom(getItem(position).lastUpdate)
        Picasso.get().load(IMG + getItem(position).imageUrl).into(holder.binding.ivLogoCoin)
        holder.itemView.setOnClickListener{
           val coinInfo = getItem(position)
            onclick?.onCoinClick(coinInfo)
        }
    }

    interface OnClickItem{
        fun onCoinClick(coinInfo: CoinInfo)
    }

}