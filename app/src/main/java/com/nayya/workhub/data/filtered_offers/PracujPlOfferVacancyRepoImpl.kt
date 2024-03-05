package com.nayya.workhub.data.filtered_offers

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.google.gson.Gson
import com.nayya.workhub.domain.entity.offer.repo.PracujPlOfferVacancyRepo
import com.nayya.workhub.domain.entity.offer.vacansy_dto.VacancyHeadingEntity
import com.nayya.workhub.domain.entity.offer.vacansy_dto.addition.VacancyAdditionEntity
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class PracujPlOfferVacancyRepoImpl(
    private val context: Context
) : PracujPlOfferVacancyRepo {
    val client = OkHttpClient()

    val gson = Gson()

    val handler: Handler = Handler(Looper.getMainLooper())

    override fun extractOfferHeading(url: String, listener: (VacancyHeadingEntity) -> Unit) {

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

                    var tmpString = body
                        .substringAfter(
                            "<script>window.GTMRawUrl="
                        )

                    tmpString =
                        tmpString
                            .substringAfter(
                                "</script><script type=\"application/ld+json\">"
                            )

                    tmpString =
                        tmpString
                            .substringBefore(
                                "</script><script type=\"text/javascript\">"
                            )

                    val jobEntity = gson.fromJson(
                        tmpString,
                        VacancyHeadingEntity::class.java
                    )

                    handler.post {
                        listener.invoke(jobEntity)
                    }

                    Log.d("@@@", "onResponse() called with: call = $call, response = $response")

                }
            }
        })
    }

    override fun extractOfferAddition(url: String, listener: (VacancyAdditionEntity) -> Unit) {

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

                    var tmpString = body
                        .substringAfter(
                            "<script>window.GTMRawUrl="
                        )

                    tmpString =
                        tmpString
                            .substringAfter(
                                "</script><script>window['kansas-offerview'] = "
                            )

                    tmpString =
                        tmpString.substringBefore("</script>")

//                    val jobEntity: VacancyAdditionEntity = gson.fromJson(
//                        tmpString.replace("undefined", "\"\""),
//                        VacancyAdditionEntity::class.java
//                    )
                    val modifiedString = tmpString.replace(
                        "undefined",
                        "null"
                    )
                    val jobEntity: VacancyAdditionEntity =
                        gson.fromJson(
                            modifiedString,
                            VacancyAdditionEntity::class.java
                        )

                    handler.post {
                        listener.invoke(jobEntity)
                    }

                    Log.d("@@@", "onResponse() called with: call = $call, response = $response")

                }
            }
        })
    }
}