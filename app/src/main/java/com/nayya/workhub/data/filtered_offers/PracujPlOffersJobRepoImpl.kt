package com.nayya.workhub.data.filtered_offers

import android.content.Context
import android.os.Handler
import android.os.Looper
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.nayya.workhub.domain.entity.offer.OfferJob
import com.nayya.workhub.domain.entity.offer.OfferListItem
import com.nayya.workhub.domain.entity.offer.repo.PracujPlOffersJobRepo
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException


class PracujPlOffersJobRepoImpl(
    private val context: Context
) : PracujPlOffersJobRepo {

    private val client = OkHttpClient()

    //    val gson = Gson()
    val gson: Gson = GsonBuilder()
        .setLenient() // позволяет принять не правильный gson
        .create()

    val handler: Handler = Handler(Looper.getMainLooper())

    var offerListItem = mutableListOf<OfferListItem>()

    override fun extractOffers(url: String, listener: (List<OfferListItem>) -> Unit) {

        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                // Обработка ошибки при выполнении запроса
            }

            override fun onResponse(call: Call, response: Response) {

                val body = response.body?.string()

                // Обработка ответа от сервера
                if (response.isSuccessful && body != null) {

                    var tmpString =
                        body.substringAfter("script#__NEXT_DATA__")

                    tmpString =
                        tmpString.substringAfter("{\"groupedOffers\":")

                    tmpString =
                        tmpString.substringBefore(",\"offersTotalCount\"")

                    offerListItem = gson.fromJson(
                        tmpString, Array<OfferListItem>::class.java
                    ).toMutableList()

                    handler.post {
                        listener.invoke(offerListItem.toList())
                    }
                }
            }
        })
    }

    override fun getCities(groupId: String, callback: (List<OfferJob>) -> Unit) {
//        val city = offerListItem
//            .filter { offerList ->
//                offerList.groupId == groupId
//            }
//        callback(city)

        val cities = offerListItem
            .filter { offerList ->
                offerList.groupId == groupId
            }
            .flatMap { offerList ->
                offerList.offers ?: emptyList()
            }
        callback(cities)
    }
}