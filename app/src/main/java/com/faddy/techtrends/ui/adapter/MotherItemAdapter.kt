package com.faddy.techtrends.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.faddy.techtrends.databinding.ItemViewMotherArticleBinding

class MotherItemAdapter : RecyclerView.Adapter<MotherItemAdapter.ViewHolder>() {

    val paymentDataList = mutableListOf<String>()
    var onItemClicked: ((ipId: String) -> Unit)? = null
    var threeDotIcon: ((position: Int) -> Unit)? = null

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    inner class ViewHolder(var binding: ItemViewMotherArticleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                onItemClicked?.invoke(adapterPosition.toString())
            }
            binding.menuIcon.setOnClickListener {
                threeDotIcon?.invoke(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemViewMotherArticleBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount() = paymentDataList.size

    fun initData(passedData: List<String>) {
        paymentDataList.clear()
        paymentDataList.addAll(passedData)
        notifyDataSetChanged()
    }


}