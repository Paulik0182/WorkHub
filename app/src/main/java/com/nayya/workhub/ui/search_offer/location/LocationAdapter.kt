package com.nayya.workhub.ui.search_offer.location

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.nayya.workhub.R
import com.nayya.workhub.domain.entity.filter_category.LocationEntity
import java.util.Locale

class LocationAdapter(
    private var data: List<Pair<LocationEntity, Boolean>> = mutableListOf(),
    private var context: Context,
    val listener: (String, Boolean) -> Unit,
) : RecyclerView.Adapter<LocationViewHolder>() {

    private var filteredData: List<Pair<LocationEntity, Boolean>> = mutableListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(location: List<Pair<LocationEntity, Boolean>>) {
        data = location
        filteredData = location
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        return LocationViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.item_location, parent, false
                ),
            context,
            listener
        )
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private fun getItem(position: Int): Pair<LocationEntity, Boolean> {
        return data[position]
    }

    @SuppressLint("NotifyDataSetChanged")
    fun filterList(query: String?) {
        if (query.isNullOrEmpty()) {
            filteredData = data
        } else {
            filteredData = data.filter { pair ->
                pair.first.locality.lowercase(Locale.ROOT).contains(query.lowercase(Locale.ROOT))
            }
        }
        notifyDataSetChanged()
    }

    fun getFilteredData(query: String?): List<Pair<LocationEntity, Boolean>> {
        if (query.isNullOrEmpty()) {
            return data
        } else {
            val filteredData = data.filter { pair ->
                pair.first.locality.lowercase(Locale.ROOT).contains(query.lowercase(Locale.ROOT))
            }
            if (filteredData.isEmpty()) {
                Toast.makeText(context, "Нет такого города", Toast.LENGTH_SHORT).show()
            }
            return filteredData
        }
    }

    fun filterList1(query: String?) {
        val location = data
        if (query != null) {
            val filteredList = ArrayList<LocationEntity>()
            for (value in location) {
                if (value.first.locality.lowercase(Locale.ROOT).contains(query)) {
                    filteredList.add(value.first)
                }
            }
            if (filteredList.isEmpty()) {
                Toast.makeText(context, "Нет такого города", Toast.LENGTH_SHORT).show()
            }
            // Обновить данные в адаптере
            setData(filteredList.map {
                Pair(it, false)
            })
        }
    }
}

class LocationViewHolder(
    initView: View,
    var context: Context,
    val listener: (String, Boolean) -> Unit
) : RecyclerView.ViewHolder(initView) {

    private val textView = initView.findViewById<TextView>(R.id.location_text_view)

    fun bind(location: Pair<LocationEntity, Boolean>) {
        val locationEntity = location.first

        textView.text = locationEntity.locality

        textView.setOnClickListener {
            listener.invoke(locationEntity.id.toString(), true)
        }
    }

    init {
        initView.setOnClickListener {
            Toast.makeText(context, it.toString(), Toast.LENGTH_SHORT).show()
        }
    }
}