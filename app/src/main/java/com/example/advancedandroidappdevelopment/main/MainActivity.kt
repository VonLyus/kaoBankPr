package com.example.advancedandroidappdevelopment.main

import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.advancedandroidappdevelopment.R
import com.example.advancedandroidappdevelopment.data.SearchItemModel
import com.example.advancedandroidappdevelopment.databinding.ActivityMainBinding
import com.example.advancedandroidappdevelopment.searchresult.SearchResultFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    var likedItems: ArrayList<SearchItemModel> = ArrayList()

    private val viewPagerAdapter by lazy {
        MainViewPagerAdapter(this@MainActivity)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()


    }

    private fun initView() = with(binding){
//        toolbar.title = getString(R.string.app_name)

        viewPager2.adapter = viewPagerAdapter
        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (viewPagerAdapter.getFragment(position) is SearchResultFragment) {
//                    toolbar.visibility = View.VISIBLE
                } else {
//                    toolbar.visibility = View.GONE
                }
            }
        })

        TabLayoutMediator(tabLayout, viewPager2){
            tab , position ->
            tab.setText(viewPagerAdapter.getTitle(position))
            tab.setIcon(viewPagerAdapter.getIcon(position))
        }.attach()

        // TabLayout에 TabSelectedListener 설정
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                // 선택된 탭의 아이콘 색상 변경
                tab?.icon?.setColorFilter(ContextCompat.getColor(this@MainActivity, R.color.white), PorterDuff.Mode.SRC_IN)

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                // 선택 해제된 탭의 아이콘 색상 변경
                tab?.icon?.setColorFilter(ContextCompat.getColor(this@MainActivity, R.color.tab_layout_btn_shape_color), PorterDuff.Mode.SRC_IN)

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                // 다시 선택된 경우 추가 작업 수행
            }
        })
    }

    fun addLikedItem(item: SearchItemModel){
        if(!likedItems.contains(item)){
            likedItems.add(item)
        }
    }

    fun removeLikedItem(item: SearchItemModel) {
        likedItems.remove(item)
    }

}