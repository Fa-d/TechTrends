package com.faddy.techtrends.ui.adapter

import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.util.contains
import androidx.recyclerview.widget.RecyclerView
import com.faddy.techtrends.R
import com.faddy.techtrends.databinding.ItemViewTopicBinding
import com.faddy.techtrends.models.newModels.CategoryModel

class TopicAdapter : RecyclerView.Adapter<TopicAdapter.ViewHolder>() {
    val paymentDataList = mutableListOf<CategoryModel>()
    var onItemClicked: ((dataItem: CategoryModel) -> Unit)? = null
    private val selectedItems: SparseBooleanArray = SparseBooleanArray()

    inner class ViewHolder(var binding: ItemViewTopicBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener { dataItem ->
                onItemClicked?.invoke(paymentDataList[adapterPosition].apply {
                    this.isItemSelected = !this.isItemSelected
                })
                multipleSelection(adapterPosition)
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
        binding.topicTitle.text = dataItem.cat_name
        selectedItems[position, false]
        binding.root.background = if (selectedItems[position, false]) {
            ContextCompat.getDrawable(binding.root.context, R.drawable.rounded_selected_item)
        } else {
            ContextCompat.getDrawable(
                binding.root.context, R.drawable.rounded_counrty_back_selected
            )
        }
    }


    fun multipleSelection(pos: Int) {
        if (selectedItems.contains(pos)) {
            selectedItems.delete(pos)
        } else {
            selectedItems.put(pos, true)
        }
        notifyItemChanged(pos)
    }

    fun initData(passedData: List<CategoryModel>) {
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