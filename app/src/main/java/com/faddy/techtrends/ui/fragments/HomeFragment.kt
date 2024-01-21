package com.faddy.techtrends.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.activityViewModels
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.faddy.techtrends.core.MainViewModel
import com.faddy.techtrends.databinding.FragmentHomeBinding
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment() {
    private val mainViewModel: MainViewModel by activityViewModels()
    private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return FragmentHomeBinding.inflate(layoutInflater).also { binding = it }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initData()
        initClickListener()
        initObserver()
    }

    private fun initObserver() {

    }

    private fun initClickListener() {


    }

    private fun initData() {

    }

    private fun initView() {
        val pagerAdapter = ScreenSlidePagerAdapter(requireActivity())
        binding.detailViewPager.adapter = pagerAdapter

        TabLayoutMediator(binding.tabLayout, binding.detailViewPager) { tab, position ->
            tab.text = "Tab ${position + 1}"
        }.attach()
    }

    private inner class ScreenSlidePagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = 2

        override fun createFragment(position: Int): Fragment = ScreenSlidePageFragment()
    }
}