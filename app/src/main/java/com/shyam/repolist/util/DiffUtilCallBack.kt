package com.shyam.repolist.util

import androidx.recyclerview.widget.DiffUtil
import com.shyam.repolist.db.model.Repository

class DiffUtilCallBack : DiffUtil.ItemCallback<Repository>() {
    override fun areItemsTheSame(oldItem: Repository, newItem: Repository): Boolean {
        return oldItem.fullName == newItem.fullName
                && oldItem.owner == newItem.owner
    }

    override fun areContentsTheSame(oldItem: Repository, newItem: Repository): Boolean {
        return oldItem.fullName == newItem.fullName
                && oldItem.owner == newItem.owner
                && oldItem.createdOn == newItem.createdOn
    }
}