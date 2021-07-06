package com.shyam.repolist.ui.avatar

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shyam.repolist.db.model.RepoOwner


class AvatarViewModel : ViewModel() {
    private val imageLiveData: MutableLiveData<RepoOwner> = MutableLiveData<RepoOwner>()

    companion object {
        private const val IMAGE_ARG = "image"
        fun createArguments(owner: RepoOwner?): Bundle {
            val bundle = Bundle()
            bundle.putSerializable(IMAGE_ARG, owner)
            return bundle
        }
    }

    fun loadArguments(arguments: Bundle?) {
        if (arguments == null) {
            return
        }

        val image: RepoOwner? = arguments.get(IMAGE_ARG) as RepoOwner?
        image?.let {
            imageLiveData.value = it
        }
    }

    fun getImageDetailData(): LiveData<RepoOwner> {
        return imageLiveData
    }
}