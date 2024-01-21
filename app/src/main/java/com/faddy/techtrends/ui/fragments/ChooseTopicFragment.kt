package com.faddy.techtrends.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.faddy.techtrends.R
import com.faddy.techtrends.core.MainViewModel
import com.faddy.techtrends.databinding.FragmentChooseTopicBinding
import com.faddy.techtrends.ui.adapter.TopicAdapter
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ChooseTopicFragment : Fragment() {
    private lateinit var binding: FragmentChooseTopicBinding
    private val mainViewModel: MainViewModel by activityViewModels()
    private val topicAdapter = TopicAdapter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return FragmentChooseTopicBinding.inflate(layoutInflater).also { binding = it }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initData()
        initObserver()
        initClickListener()
    }

    private fun initObserver() {
        mainViewModel.allCategoriesList.observe(viewLifecycleOwner) { dataList ->
            val tempCat = mutableListOf<String>()
            dataList.forEach { result ->
                result.values.forEach { tempCat.add(it) }
            }
            //insert the response into mainDatabase
            topicAdapter.initData(tempCat)
        }
    }

    private fun initClickListener() {
        binding.nextButton.setOnClickListener {
            findNavController().navigate(R.id.homeFragment)
        }
    }

    private fun initData() {
        var layoutManager2 = FlexboxLayoutManager(context)
        layoutManager2.flexDirection = FlexDirection.ROW
        layoutManager2.justifyContent = JustifyContent.SPACE_EVENLY
        with(binding.topicRecycler) {
            layoutManager = layoutManager2
            //  layoutManager = StaggeredGridLayoutManager(4, LinearLayoutManager.HORIZONTAL)
            adapter = topicAdapter
        }

    }

    private fun initView() {
        mainViewModel.getAllCategoriesData()
    }
}