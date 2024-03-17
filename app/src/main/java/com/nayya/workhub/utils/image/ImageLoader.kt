package com.nayya.workhub.utils.image

interface ImageLoader<T> {

    fun loadInto(url: String, container: T)
}