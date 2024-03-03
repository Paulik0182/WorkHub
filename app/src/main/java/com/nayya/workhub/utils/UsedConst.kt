package com.nayya.workhub.utils

import android.annotation.SuppressLint
import com.nayya.workhub.R

class UsedConst {

    @SuppressLint("NotConstructor")
    private fun UsedConst() {
        //для того чтобы нельзя было чтото сделать с этим классом
    }

    interface PracujPlHttpsConst {
        companion object {
            const val BASIC_URL_KEY = "https://it.pracuj.pl/praca/android;kw"
        }
    }

    interface ImageConst {
        companion object {
            fun getDefaultImageResourceId(): Int {
                return R.drawable.fragment_job_look
            }

//            const val DEFAULT_IMAGE_CONST = R.drawable.fragment_job_look
        }
    }
}