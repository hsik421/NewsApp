package com.project.news.ui.view

import com.project.news.ui.BaseAct
import com.project.news.R
import com.project.news.data.ViewConstants
import com.project.news.databinding.ActWebviewBinding

class WebViewAct : BaseAct<ActWebviewBinding>(R.layout.act_webview) {
    override fun initView() {
        intent.getStringExtra(ViewConstants.VIEW_URL_PARAM)?.let {
            dataBinding.webview.loadUrl(it)
        }
    }
    override fun initObserver() = Unit
}