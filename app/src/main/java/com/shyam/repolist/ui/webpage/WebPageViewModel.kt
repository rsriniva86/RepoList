package com.shyam.repolist.ui.webpage

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shyam.repolist.db.model.Repository

class WebPageViewModel : ViewModel() {
    private val imageLiveData: MutableLiveData<Repository> = MutableLiveData<Repository>()

    companion object {
        private const val WEB_PAGE_ARG = "webPage"
        fun createArguments(repositoryInfo: Repository): Bundle {
            val bundle = Bundle()
            bundle.putSerializable(WEB_PAGE_ARG, repositoryInfo)
            return bundle
        }
    }

    fun loadArguments(arguments: Bundle?) {
        if (arguments == null) {
            return
        }
        val image: Repository? = arguments.get(WEB_PAGE_ARG) as Repository?
        image?.let {
            imageLiveData.value = it

        }
    }

    fun getImageDetailData(): LiveData<Repository> {
        return imageLiveData
    }
}