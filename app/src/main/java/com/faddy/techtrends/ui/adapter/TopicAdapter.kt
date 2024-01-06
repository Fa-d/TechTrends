package com.faddy.techtrends.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.faddy.techtrends.databinding.ItemViewTopicBinding

class TopicAdapter : RecyclerView.Adapter<TopicAdapter.ViewHolder>() {
    val paymentDataList = mutableListOf<String>()
    var onItemClicked: ((ipId: String) -> Unit)? = null

    inner class ViewHolder(var binding: ItemViewTopicBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener { dataItem ->
                onItemClicked?.invoke(paymentDataList[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemViewTopicBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount() = paymentDataList.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val binding = holder.binding
        val dataItem = paymentDataList[position]
        binding.topicTitle.text = dataItem
    }

    fun initData(passedData: List<String>) {
        paymentDataList.clear()
        paymentDataList.addAll(passedData)
        notifyDataSetChanged()
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

}