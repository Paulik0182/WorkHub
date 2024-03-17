package com.nayya.workhub.data.filtered_offers

import android.os.Handler
import android.os.Looper
import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonElement
import com.google.gson.JsonParser
import com.google.gson.JsonSyntaxException
import com.nayya.workhub.domain.entity.pracuj_pl_for_filter.CategoryX
import com.nayya.workhub.domain.entity.pracuj_pl_for_filter.PracujPlCategoryEntity
import com.nayya.workhub.domain.entity.pracuj_pl_for_filter.repo.PracujPlCategoryFilterRepo
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import java.util.ArrayDeque

class PracujPlCategoryFilterRepoImpl : PracujPlCategoryFilterRepo {

    private val client = OkHttpClient()

    //    val gson = Gson()
    val gson: Gson = GsonBuilder()
        .setLenient() // позволяет принять не правильный gson
        .create()

    val handler: Handler = Handler(Looper.getMainLooper())

    override fun setCategoryFilter(url: String, listener: (List<CategoryX>) -> Unit) {

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
//                        tmpString.substringAfter("type=\"application/json\">")
                        tmpString.substringAfter("\"dictionaries\":{\"categories\":")

                    tmpString =
//                        tmpString.substringBefore("</script><script>(function(){var js = \"window")
                        tmpString.substringBefore("],\"contractTypes\"")

                    val jsonArrayString = "$tmpString]"

                    val categoryXEntity =
                        gson.fromJson(
                            jsonArrayString, Array<CategoryX>::class.java
                        ).toMutableList()

                    handler.post {
                        listener.invoke(categoryXEntity.toList())
                    }

                    try {
                    } catch (e: JsonSyntaxException) {
                        val errorMessage = e.message ?: ""
                        val startIndex = errorMessage.indexOf("at line ")
                        val endIndex = errorMessage.indexOf(" column ")

                        if (startIndex != -1 && endIndex != -1) {
                            val line = errorMessage.substring(startIndex + 8, endIndex).toInt()
                            val fieldName = getFieldAtLine(
                                tmpString,
                                line
                            ) // Вызываем функцию для определения названия поля

                            Log.d(
                                "@@@",
                                "PracujPlCategoryFilterRepoImpl" +
                                        " Ошибка при разборе JSON. " +
                                        "Найдено неверное поле: = $fieldName"
                            )
                        } else {
                            Log.d(
                                "@@@",
                                "PracujPlCategoryFilterRepoImpl" +
                                        " Ошибка при разборе JSON: $errorMessage"
                            )
                        }
                        emptyList<PracujPlCategoryEntity>().toMutableList()
                    }
//                    handler.post {
//                        listener.invoke(pracujPlCategoryEntity.toList())
//                    }
                }
            }
        })
    }

    private fun getFieldAtLine(jsonString: String, line: Int): String? {
        val jsonElement = JsonParser.parseString(jsonString)

        val fieldName = findFieldName(jsonElement, line)

        return fieldName
    }

    private fun findFieldName(jsonElement: JsonElement, targetLine: Int): String? {
        val queue = ArrayDeque<Pair<JsonElement, String>>()
        queue.add(jsonElement to "")

        val jsonString = jsonElement.toString()
        val fieldLine = getFieldLine(jsonString, "path.to.field")

        var currentLine = 1

        while (queue.isNotEmpty()) {
            val (element, path) = queue.poll()

            if (element.isJsonObject) {
                val jsonObject = element.asJsonObject

                for ((key, value) in jsonObject.entrySet()) {
                    val newPath = if (path.isEmpty()) key else "$path.$key"
                    queue.add(value to newPath)
                }

            } else if (element.isJsonArray) {
                val jsonArray = element.asJsonArray

                for ((index, value) in jsonArray.withIndex()) {
                    val newPath = if (path.isEmpty()) "[$index]" else "$path[$index]"
                    queue.add(value to newPath)
                }

            }

            if (fieldLine != null && fieldLine == targetLine) {
                return path
            }

            currentLine++
        }

        return null
    }

    private fun getFieldLine(jsonString: String, fieldPath: String): Int? {
        val lines = jsonString.split("\n")

        for ((index, line) in lines.withIndex()) {
            if (line.contains(fieldPath)) {
                return index + 1 // начинаем счет с 1, поэтому добавляем 1
            }
        }

        return null
    }
}