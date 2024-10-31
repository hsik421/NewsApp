package com.project.news.utils

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.project.news.R
import java.io.File

@BindingAdapter("imgFile")
fun ImageView.setImage(file: File?) {
    Glide.with(this).load(file).error(R.mipmap.ic_launcher).skipMemoryCache(true)
        .into(this)
}

@BindingAdapter("imgUrlOrDownload", "fileName")
fun ImageView.setDownloadImage(imgUrl: String?, fileName: String) {
    imgUrl?.let {
        val file = File(context.filesDir, "${fileName}.jpg")
        if (file.exists()) {
            this.setImage(file)
        } else {
            Glide.with(this)
                .download(imgUrl)
                .addListener(DownloadRequestListener(file = file, imageView = this))
                .submit()
        }

    } ?: return this@setDownloadImage.setImage(null)
}

@BindingAdapter("setVisible")
fun View.setVisible(visible: Boolean) {
    if (visible)
        this.visibility = View.VISIBLE
    else
        this.visibility = View.GONE
}