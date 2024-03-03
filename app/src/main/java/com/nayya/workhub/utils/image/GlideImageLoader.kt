package com.nayya.workhub.utils.image

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.nayya.workhub.utils.UsedConst

class GlideImageLoader : ImageLoader<ImageView> {

    private val imageView = UsedConst.ImageConst.getDefaultImageResourceId()
    override fun loadInto(url: String, container: ImageView) {
        Glide.with(container.context.applicationContext)
            .asBitmap()
            .load(url)
            .placeholder(imageView)
//            .circleCrop() // Делает отображение в круглой форме
            .into(container)
    }
}