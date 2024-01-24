package com.nayya.workhub.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

/**
 * экстеншен (расширение обычной чужой функции). Можно указать mutable расширение и оно вернет
 * версию MutableLiveData это сделано чтобы во фрагменте случайно не изменить список (в этом
 * рельной безописности нет)
 */
fun <T> LiveData<T>.mutable(): MutableLiveData<T> {
    return this as MutableLiveData
}