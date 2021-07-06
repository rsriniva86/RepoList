package com.shyam.repolist.ui.webpage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.shyam.repolist.R
import com.shyam.repolist.util.initToolbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.webpage_fragment.*

@AndroidEntryPoint
class WebPageFragment : Fragment() {
    private val viewModel: WebPageViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.webpage_fragment, container, false)
        viewModel.loadArguments(arguments)
        return view
    }

    @Suppress("DEPRECATION")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initToolbar(toolbar1, R.string.web_page, true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        img_webview.webViewClient = WebViewClient()
        viewModel.getImageDetailData().observe(viewLifecycleOwner, {
            val url = it?.website
            url?.let {
                img_webview.loadUrl(url)
            }
        })
    }


}