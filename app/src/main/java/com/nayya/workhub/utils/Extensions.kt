package com.nayya.workhub.utils

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.text.SimpleDateFormat

/**
 * экстеншен (расширение обычной чужой функции). Можно указать mutable расширение и оно вернет
 * версию MutableLiveData это сделано чтобы во фрагменте случайно не изменить список (в этом
 * рельной безописности нет)
 */
fun <T> LiveData<T>.mutable(): MutableLiveData<T> {
    return this as MutableLiveData
}

@SuppressLint("SimpleDateFormat")
var bpDataFormatter = SimpleDateFormat("dd MMMM yyyy")

fun <T> List<T>.toFormattedString(): String {
    return this.toString()
        .removeSurrounding("[", "]")
}