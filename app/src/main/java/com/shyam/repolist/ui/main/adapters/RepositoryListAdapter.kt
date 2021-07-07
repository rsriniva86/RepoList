package com.shyam.repolist.ui.main.adapters

import android.graphics.Paint
import android.os.Build
import android.text.Html
import android.util.Log
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
import kotlinx.android.synthetic.main.item_repo.view.*

class RepositoryListAdapter :
    PagingDataAdapter
    <Repository,
     RepositoryListAdapter.RepositoryViewHolder>(DiffUtilCallBack()) {

    private var itemClickListener: ItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_repo, parent, false)
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
        private val contents: TextView = itemView.contents
        private val tvWebPageUrl: TextView = itemView.tvWebPageUrl

        fun bindData(repository: Repository) {
            with(repository) {
                repository.owner?.links?.avatar?.href?.let{
                    Log.i(TAG,"Loading Image for $it")
                    Glide.with(itemView.context)
                        .load(it)
                        .transform(
                            CenterCrop(),
                            RoundedCorners(25)
                        )
                        .error(R.drawable.user_avatar)
                        .placeholder(R.drawable.user_avatar)
                        .into(ivImageCover)
                }

                var html=""
                name?.let {
                    if(it.isNotEmpty()) {
                        html += "<font color='blue'>Name=</font>$it<br>"
                    }
                }
                type?.let{
                    if(it.isNotEmpty()) {
                        html += "<font color='blue'>type=</font>${it}<br>"
                    }
                }
                createdOn?.let{
                    if(it.isNotEmpty()) {
                        html += "<font color='blue'>createdin=</font>${it}<br>"
                    }
                }
                scm?.let{
                    if(it.isNotEmpty()) {
                        html += "<font color='blue'>scm=</font>$it<br>"
                    }
                }
                owner?.displayName?.let{
                    if(it.isNotEmpty()) {
                        html += "<font color='blue'>owner=</font>${it}<br>"
                    }
                }

                language?.let{
                    if(it.isNotEmpty()) {
                        html += "<font color='blue'>language=</font>${it}<br>"
                    }
                }
                contents.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    Html.fromHtml(html, Html.FROM_HTML_MODE_COMPACT)
                } else {
                    Html.fromHtml(html)
                }
                tvWebPageUrl.text = website
                tvWebPageUrl.paintFlags = Paint.UNDERLINE_TEXT_FLAG
            }
        }
    }

    interface ItemClickListener {
        fun onItemClick(repository: Repository)
        fun onUrlClick(repository: Repository)
    }
    companion object{
        val TAG=RepositoryListAdapter::class.simpleName
    }
}