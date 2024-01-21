package com.faddy.techtrends.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.faddy.techtrends.databinding.FragmentScreenSlidePageBinding
import com.faddy.techtrends.ui.adapter.MotherItemAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ScreenSlidePageFragment() : Fragment() {
    private lateinit var binding: FragmentScreenSlidePageBinding
    private val motherItemAdapter = MotherItemAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return FragmentScreenSlidePageBinding.inflate(layoutInflater).also { binding = it }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initClickListener()
    }

    private fun initClickListener() {
        motherItemAdapter.threeDotIcon = {
            ServerListBottomSheetFragment().show(
                childFragmentManager, "ServerListBottomSheetFragment"
            )
        }
    }

    private fun initView() {
        with(binding.mainRecycler) {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            adapter = motherItemAdapter
        }
        motherItemAdapter.initData(listOf("", "", "", "", ""))
    }

}