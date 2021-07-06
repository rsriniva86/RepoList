package com.shyam.repolist.ui.main.adapters

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.shyam.repolist.R
import com.shyam.repolist.db.model.Repository
import com.shyam.repolist.util.DiffUtilCallBack
import kotlinx.android.synthetic.main.item_image.view.*

class RepositoryListAdapter :
    PagingDataAdapter
    <Repository,
     RepositoryListAdapter.RepositoryViewHolder>(DiffUtilCallBack()) {

    private var itemClickListener: ItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false)
        return RepositoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        getItem(position)?.let { holder.bindData(it) }
        val item = getItem(position)
        holder.itemView.ivCover.setOnClickListener {
            item?.let { it1 -> itemClickListener?.onItemClick(it1) }
        }
        holder.itemView.tvWebPageUrl.setOnClickListener {
            item?.let { it1 -> itemClickListener?.onUrlClick(it1) }
        }


    }

    fun setOnItemClickListener(listenerSearch: ItemClickListener) {
        itemClickListener = listenerSearch
    }

    class RepositoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivImageCover: ImageView = itemView.ivCover
        private val tvTitle: TextView = itemView.tvTitle
        private val tvWebPageUrl: TextView = itemView.tvWebPageUrl

        fun bindData(repository: Repository) {
            with(repository) {
                Glide.with(itemView.context)
                    .load(repository.owner?.avatar?.href)
                    .transform(CenterCrop(), RoundedCorners(25))
                    .into(ivImageCover)
                tvTitle.text = fullName
                tvWebPageUrl.text = website
                tvWebPageUrl.paintFlags = Paint.UNDERLINE_TEXT_FLAG
            }
        }
    }

    interface ItemClickListener {
        fun onItemClick(repository: Repository)
        fun onUrlClick(repository: Repository)
    }
}