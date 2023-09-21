package com.example.advancedandroidappdevelopment.storagebox

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.advancedandroidappdevelopment.Utils.getDateFromTimestampWithFormat
import com.example.advancedandroidappdevelopment.data.SearchItemModel
import com.example.advancedandroidappdevelopment.databinding.SearchResultItemBinding
import com.example.advancedandroidappdevelopment.main.MainActivity
import com.example.advancedandroidappdevelopment.searchresult.SearchResultFragmentAdapter

class StorageBoxFragmentAdapter(var mContext: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var items = mutableListOf<SearchItemModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = SearchResultItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Glide.with(mContext)
            .load(items[position].url)
            .into((holder as ItemViewHolder).iv_search_result_item)

        holder.tv_title.text = items[position].title
        holder.iv_like.visibility = View.GONE
        holder.tv_datetime.text =
            getDateFromTimestampWithFormat(
                items[position].dateTime,
                "yyyy-MM-dd'T'HH:mm:ss.SSS+09:00",
                "yyyy-MM-dd HH:mm:ss"
            )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ItemViewHolder(binding: SearchResultItemBinding): RecyclerView.ViewHolder(binding.root){
        var iv_search_result_item: ImageView = binding.ivSearchResultItem
        var iv_like: ImageView = binding.ivLike
        var tv_title: TextView = binding.tvTitle
        var tv_datetime: TextView = binding.tvDatetime
        var search_result_item = binding.searchResultItem

        init {
            iv_like.visibility = View.GONE
            search_result_item.setOnClickListener{

                val position = adapterPosition
                (mContext as MainActivity).removeLikedItem(items[position])

                if(position != RecyclerView.NO_POSITION){
                    items.removeAt(position)
                    notifyItemRemoved(position)

                }
            }
        }
    }

}