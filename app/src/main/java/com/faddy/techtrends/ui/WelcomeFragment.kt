package com.faddy.techtrends.ui

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.faddy.techtrends.R
import com.faddy.techtrends.databinding.FragmentWelcomeBinding


class WelcomeFragment : Fragment() {
    private lateinit var binding: FragmentWelcomeBinding
    var isSeeMore = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return FragmentWelcomeBinding.inflate(layoutInflater).also { binding = it }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClickListener()
    }

    private fun initClickListener() {
        binding.seeMoreText.setOnClickListener {
            if (isSeeMore) {
                binding.subtitleText.text =
                    Html.fromHtml(getString(R.string.tos_content), Html.FROM_HTML_MODE_COMPACT)
                binding.seeMoreText.text = "See Less"
            } else {
                binding.subtitleText.text =
                    "A Terms and Conditions agreement acts as a legal contract between you (the company) and the user. It's where you maintain your rights to exclude users from your app in the event that they abuse your website/app, set out the rules for using your service and note other important details and"
                binding.seeMoreText.text = "See More"
            }
            isSeeMore = !isSeeMore
        }
    }
//http://ec2-18-142-15-245.ap-southeast-1.compute.amazonaws.com/tableQuery
}