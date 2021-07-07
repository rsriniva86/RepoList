package com.shyam.repolist.ui.avatar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.shyam.repolist.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.avatar_dialog.view.*


@AndroidEntryPoint
class AvatarDialogFragment : DialogFragment() {

    private val viewModel: AvatarViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.avatar_dialog, container, false)
        viewModel.loadArguments(arguments)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getImageDetailData().observe(this, {
            it.links?.avatar?.let {
                Glide.with(view.context)
                    .load(it.href)
                    .transform(CenterCrop())
                    .error(R.drawable.user_avatar)
                    .placeholder(R.drawable.user_avatar)
                    .into(view.imgDetail)
            }

        })
    }
}