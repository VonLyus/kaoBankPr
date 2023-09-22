package com.example.advancedandroidappdevelopment.searchresult

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.advancedandroidappdevelopment.Utils
import com.example.advancedandroidappdevelopment.data.Constants
import com.example.advancedandroidappdevelopment.data.model.ImageSearchResponse
import com.example.advancedandroidappdevelopment.data.Retrofit_instance.apiService
import com.example.advancedandroidappdevelopment.data.SearchItemModel
import com.example.advancedandroidappdevelopment.data.model.VideoSearchResponse
import com.example.advancedandroidappdevelopment.databinding.FragmentSearchResultBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchResultFragment : Fragment() {

    private lateinit var binding : FragmentSearchResultBinding
    private lateinit var mContext: Context
    private lateinit var adapter: SearchResultFragmentAdapter
    private lateinit var gridmanager: StaggeredGridLayoutManager

    private var resItems: ArrayList<SearchItemModel> = ArrayList()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    companion object {
        fun newInstance() = SearchResultFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSearchResultBinding.inflate(inflater, container, false)

        setupViews()
        setupListeners()

        return binding.root

    }

    private fun setupViews(){
        gridmanager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.rvSearchResult.layoutManager = gridmanager

        adapter = SearchResultFragmentAdapter(mContext)
        binding.rvSearchResult.adapter = adapter
        binding.rvSearchResult.itemAnimator = null

        val lastSearch = Utils.getLastSearch(requireContext())
        binding.etSearch.setText(lastSearch)
    }

    private fun setupListeners(){
        // 키보드 숨기기 기능
        val imm =
            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        binding.tvSearch.setOnClickListener{
            val query = binding.etSearch.text.toString()
            if(query.isNotEmpty()){
                Utils.saveLastSearch(requireContext(), query)
                adapter.clearItem()
                fetchImageResults(query)
            }else{
                Toast.makeText(mContext,"검색어에 입력해주세요",Toast.LENGTH_SHORT).show()
            }


            imm.hideSoftInputFromWindow(binding.etSearch.windowToken,0)
        }
    }

    private fun fetchImageResults(query: String){
        apiService.image_search(Constants.AUTH_HEADER, query, "recency", 1, 20)
            ?.enqueue(object : Callback<ImageSearchResponse?>{
            override fun onResponse(
                call: Call<ImageSearchResponse?>,
                response: Response<ImageSearchResponse?>
            ) {
                Log.d("fetch","response = ${response.body()?.metaData?.totalCount}")

                response.body()?.metaData?.let{metaData ->
                    if(metaData.totalCount > 0){
                        response.body()!!.documents.forEach { documents ->
                            val title = documents.displaySitename
                            val datetime = documents.datetime
                            val url = documents.thumbnailUrl
                            resItems.add(SearchItemModel(title, datetime, url))
                        }
                    }
                }
                Log.d("fetch","resItems = ${resItems.size}")
                adapter.items = resItems
                adapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<ImageSearchResponse?>, t: Throwable) {
                Log.d("onFailure","onFailure:  ${t.message}")
            }
            })
    }
//    private fun fetchVedioResults(query: String){
//        apiService.image_search(Constants.AUTH_HEADER, query, "recency", 1, 10)
//            ?.enqueue(object : Callback<VideoSearchResponse?>{
//                override fun onResponse(
//                    call: Call<VideoSearchResponse?>,
//                    response: Response<VideoSearchResponse?>
//                ) {
//
//                }
//                override fun onFailure(call: Call<VideoSearchResponse?>, t: Throwable) {
//                    Log.d("onFailure","onFailure:  ${t.message}")
//                }
//            }
//
//    }


}

//        val repository = Repository()
//        val viewModelFactory = SearchResultViewModelFactory(repository)
//        viewModel = ViewModelProvider(this,viewModelFactory)[SearchResultViewModel::class.java]
//
//        binding.searchBtn.setOnClickListener {
//            viewModel.image_search()
//            //liveData에 값을 넣고 확인하는 작업
//            viewModel.myCustomPosts.observe(viewLifecycleOwner, Observer{
//                Log.d("Response", it.toString())
//            })
//        }