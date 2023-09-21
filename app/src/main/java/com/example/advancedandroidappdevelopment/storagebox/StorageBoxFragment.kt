package com.example.advancedandroidappdevelopment.storagebox

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.advancedandroidappdevelopment.R
import com.example.advancedandroidappdevelopment.data.SearchItemModel
import com.example.advancedandroidappdevelopment.databinding.FragmentStorageBoxBinding
import com.example.advancedandroidappdevelopment.main.MainActivity

class StorageBoxFragment : Fragment() {
    private lateinit var mContext: Context

    private var binding: FragmentStorageBoxBinding? = null
    private lateinit var adapter: StorageBoxFragmentAdapter

    private var likedItems: List<SearchItemModel> = listOf()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    companion object {
        fun newInstance() = StorageBoxFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val mainActivity = activity as MainActivity
        likedItems = mainActivity.likedItems

        adapter = StorageBoxFragmentAdapter(mContext).apply {
            items = likedItems.toMutableList()
        }

        binding = FragmentStorageBoxBinding.inflate(inflater, container, false).apply {

            rvStorageBox.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            rvStorageBox.adapter = adapter
        }


        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}