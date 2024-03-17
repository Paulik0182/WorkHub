package com.nayya.workhub.utils.image

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.nayya.workhub.utils.UsedConst

class GlideImageLoader : ImageLoader<ImageView> {

    private val imageView = UsedConst.ImageConst.getDefaultImageResourceId()
    override fun loadInto(url: String, container: ImageView) {
        Glide.with(container.context.applicationContext)
            .asBitmap()
            .placeholder(imageView)
            .load(url)
//            .circleCrop() // Делает отображение в круглой форме
            .into(container)
    }
}

//class GlideImageLoader(private val imageView: ImageView) : ImageLoader<ImageView> {
//    override fun loadInto(url: String, container: ImageView) {
//        if (container == imageView) {
//            // Если контейнер соответствует imageView, используем прямой вызов Glide для предотвращения перемешивания
//            Glide.with(container)
//                .asBitmap()
//                .placeholder(imageView)
//                .load(url)
//                .into(container)
//        } else {
//            // В противном случае, использовать оригинальный код с передачей container
//            Glide.with(container.context.applicationContext)
//                .asBitmap()
//                .placeholder(imageView)
//                .load(url)
//                .into(container)
//        }
//    }
//}