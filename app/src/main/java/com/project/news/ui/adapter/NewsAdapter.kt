package com.project.news.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.project.news.data.model.News
import com.project.news.ui.viewmodel.NewsViewModel
import com.project.news.R
import com.project.news.databinding.ItemNewsBinding

class NewsAdapter(private val viewModel: NewsViewModel) : ListAdapter<News, NewsAdapter.ViewHolder>(
    diffUtil
) {
    inner class ViewHolder(private var binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: News) {
            binding.item = item
            binding.root.setOnClickListener {
//                item.isVisited = 1
//                notifyItemChanged(position)
                if(item.isVisited == 0){
                    binding.newsTitle.setTextColor(ContextCompat.getColor(it.context, R.color.red))
                    binding.newsTime.setTextColor(ContextCompat.getColor(it.context, R.color.red))
                }
                viewModel.startWebView(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemNewsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<News>() {
            override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
                return oldItem.publishedAt == newItem.publishedAt
            }


            override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
                return oldItem == newItem
            }
        }
    }
}