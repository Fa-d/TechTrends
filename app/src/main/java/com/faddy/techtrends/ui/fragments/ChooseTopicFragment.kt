package com.faddy.techtrends.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.faddy.techtrends.R
import com.faddy.techtrends.databinding.FragmentChooseTopicBinding
import com.faddy.techtrends.ui.adapter.TopicAdapter
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ChooseTopicFragment : Fragment() {
    private lateinit var binding: FragmentChooseTopicBinding

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
        initClickListener()
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
        topicAdapter.initData(
            listOf(

                "Topidfg;hlc 1",
                "Topic kj",
                "Topic lkmlk  laks 1",
                "Topidfg;hlc 1",
                "TopicTopic 1",
                "Topic asd1",
                "Topic alskmaslkdma1",
                "Topic TopicTopic1",
                "TopicTopic 1",
                "Topic as;lfa'1",
                "Top",
                "Tolkamsdlkamsdlkapic 1",
                "Topic kj",
                "Topic lkmlk  laks 1",
                "Topic alskmaslkdma1",
                "Topic TopicTopic1",
                "TopicTopic 1",
                "Topic as;lfa'1",
                "Top",
                "Topic kj",
                "Topic lkmlk  laks 1",
                "Topidfg;hlc 1",
                "Topic asd1",
                "Topic asd1",
                "Topidfg;hlc 1",
                "Topic asd1",
                "Topic alskmaslkdma1",
                "Topic TopicTopic1",
                "TopicTopic 1",
                "Topic asd1",
                "Topidfg;hlc 1",
                "Topic asd1",
                "Topic alskmaslkdma1",
                "Topic TopicTopic1",
                "TopicTopic 1",
                "Topic alskmaslkdma1",
                "Topic TopicTopic1",

                "Topic alskmaslkdma1",
                "Topic as;lfa'1",
                "Top",
            )
        )
    }

    private fun initView() {

    }
}