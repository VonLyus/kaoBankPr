package com.example.advancedandroidappdevelopment.searchresult

import android.content.Context
import android.util.Log
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

class SearchResultFragmentAdapter
    (private val mContext:Context): RecyclerView.Adapter<SearchResultFragmentAdapter.ItemViewHolder>()
{

    var items = ArrayList<SearchItemModel>()
    fun clearItem(){
        items.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemViewHolder {
        val binding = SearchResultItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ItemViewHolder,
        position: Int
    ) {
        Log.d("fetch","position = ${position}")
        val currentItem = items[position]

        Glide.with(mContext)
            .load(currentItem.url)
            .into(holder.iv_search_result_item)

        holder.iv_like.visibility = if(currentItem.isLike)View.VISIBLE else View.INVISIBLE
        holder.tv_title.text = currentItem.title
        holder.tv_datetime.text = getDateFromTimestampWithFormat(
            currentItem.dateTime,
            "yyyy-MM-dd'T'HH:mm:ss.SSS+09:00",
            "yyyy-MM-dd HH:mm:ss"
        )
    }

    override fun getItemCount() = items.size

    inner class ItemViewHolder(binding: SearchResultItemBinding):RecyclerView.ViewHolder(binding.root), View.OnClickListener{

        var iv_search_result_item: ImageView = binding.ivSearchResultItem
        var iv_like: ImageView = binding.ivLike
        var tv_title: TextView = binding.tvTitle
        var tv_datetime: TextView = binding.tvDatetime
        var search_result_item = binding.searchResultItem

        init {
            iv_like.visibility = View.GONE
            iv_search_result_item.setOnClickListener(this)
            search_result_item.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            val position = adapterPosition.takeIf { it != RecyclerView.NO_POSITION } ?: return
            val item = items[position]
            item.isLike = !item.isLike

            if(item.isLike){
                (mContext as MainActivity).addLikedItem(item)
            }else{
                (mContext as MainActivity).removeLikedItem(item)
            }

            notifyItemChanged(position)
        }
    }
}