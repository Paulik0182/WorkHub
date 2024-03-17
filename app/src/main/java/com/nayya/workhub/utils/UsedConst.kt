package com.nayya.workhub.utils

import android.annotation.SuppressLint
import com.nayya.workhub.R

class UsedConst {

    @SuppressLint("NotConstructor")
    private fun UsedConst() {
        //для того чтобы нельзя было чтото сделать с этим классом
    }

    object PracujPlHttpsConst {
        const val BASIC_URL_KEY = "https://it.pracuj.pl/praca/android;kw"
        const val BASIC_PRACUJ_PL_URL_KEY = "https://www.pracuj.pl/praca/"

    }

    object ImageConst {
        fun getDefaultImageResourceId(): Int {
            return R.drawable.fragment_job_look

        }
        //            const val DEFAULT_IMAGE_CONST = R.drawable.fragment_job_look


//        fun getDefaultImageView(context: Context): ImageView {
//            val imageView = ImageView(context)
//            imageView.setImageResource(R.drawable.fragment_job_look)
//            // Дополнительные настройки вида ImageView, если требуется
//            return imageView
//        }
    }
}