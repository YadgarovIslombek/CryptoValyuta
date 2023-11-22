package uz.example.cryptovalyuta.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import uz.example.cryptovalyuta.databinding.ItemIconBinding
import uz.example.cryptovalyuta.data.model.CoinInfoDto

class CoinAdapter(private val list: List<CoinInfoDto>, private val onClick:(coinInfo: CoinInfoDto)->Unit) : RecyclerView.Adapter<CoinAdapter.VH>() {
    inner class VH(val binding: ItemIconBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemIconBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = list.size


    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.binding.tvSymbols.text = list[position].fromSymbol + "/" + list[position].tosymbol
        holder.binding.tvPrice.text = list[position].price.toString()
        holder.binding.tvLastUpdate.text = "Oxirgi yangilangan vaqti: "+list[position].getTimeconverted()
        Picasso.get().load(list[position].getImageLink()).into(holder.binding.ivLogoCoin)
        holder.itemView.setOnClickListener{
            onClick.invoke(list[position])
        }
    }

}