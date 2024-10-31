package com.project.news.utils

import android.widget.ImageView
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File

class DownloadRequestListener(
    private val file: File,
    private val imageView : ImageView
) : RequestListener<File> {
    override fun onLoadFailed(
        e: GlideException?,
        model: Any?,
        target: Target<File>,
        isFirstResource: Boolean
    ): Boolean {
        CoroutineScope(Dispatchers.Main).launch {
            imageView.setImage(null)
        }
        return false
    }

    override fun onResourceReady(
        resource: File,
        model: Any,
        target: Target<File>?,
        dataSource: DataSource,
        isFirstResource: Boolean
    ): Boolean {
        resource.inputStream().copyTo(file.outputStream()) {
            CoroutineScope(Dispatchers.Main).launch {
                imageView.setImage(file)
            }
        }
        return false
    }
}