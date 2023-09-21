package com.example.advancedandroidappdevelopment.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.advancedandroidappdevelopment.R
import com.example.advancedandroidappdevelopment.searchresult.SearchResultFragment
import com.example.advancedandroidappdevelopment.storagebox.StorageBoxFragment

class MainViewPagerAdapter(
    fragmentActivity: FragmentActivity
) : FragmentStateAdapter(fragmentActivity) {

    private val fragments = ArrayList<MainTabs>()

    init{
        fragments.add(
            MainTabs(SearchResultFragment.newInstance(), R.string.search_result, R.drawable.home)
        )
        fragments.add(
            MainTabs(StorageBoxFragment.newInstance(), R.string.storage_box, R.drawable.storage)
        )
    }

    fun getFragment(position: Int): Fragment {
        return fragments[position].fragment
    }

    fun getTitle(position: Int): Int {
        return fragments[position].titleResource
    }

    fun getIcon(position: Int): Int{
        return fragments[position].titleIcon
    }

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position].fragment
    }

}