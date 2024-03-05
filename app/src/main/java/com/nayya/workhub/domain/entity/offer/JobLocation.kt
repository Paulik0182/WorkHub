package com.nayya.workhub.domain.entity.offer

data class JobLocation(

    val addressCountry: String,
    val addressLocality: String,
    val addressRegion: String,
    val postalCode: String,
    val streetAddress: String
)