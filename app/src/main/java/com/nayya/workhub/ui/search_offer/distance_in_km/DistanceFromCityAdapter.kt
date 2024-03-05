package com.nayya.workhub.ui.search_offer.distance_in_km

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import com.nayya.workhub.R
import com.nayya.workhub.domain.entity.filter_category.DistanceInKmEntity

class DistanceFromCityAdapter(
    private var data: List<Pair<DistanceInKmEntity, Boolean>> = mutableListOf(),
    private var onClickDistanceListener: (String, Boolean) -> Unit
) : RecyclerView.Adapter<DistanceFromCityViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun setData(distance: List<Pair<DistanceInKmEntity, Boolean>>) {
        data = distance
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DistanceFromCityViewHolder {
        return DistanceFromCityViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.item_check_box, parent, false
                ),
            onClickDistanceListener
        )
    }

    override fun onBindViewHolder(holder: DistanceFromCityViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private fun getItem(position: Int): Pair<DistanceInKmEntity, Boolean> {
//        val entries = data.entries.toList()
//        val entry = entries[position]
//        val hashMap = HashMap<String, DistanceInKmEntity>()
//        hashMap[entry.key] = entry.value
//        return hashMap
        return data[position]
    }

    override fun getItemCount(): Int = data.size

}

class DistanceFromCityViewHolder(
    initView: View,
    private val onClickDistanceListener: (String, Boolean) -> Unit
) : RecyclerView.ViewHolder(initView) {

    private val checkBox = initView.findViewById<CheckBox>(R.id.check_box)

    fun bind(distanceInKm: Pair<DistanceInKmEntity, Boolean>) {
        checkBox.text = distanceInKm.first.distanceFromCityInKm.toString()
        checkBox.setOnCheckedChangeListener(null)
        checkBox.isChecked = distanceInKm.second

        checkBox.setOnCheckedChangeListener { _, isChecksd ->
            onClickDistanceListener(distanceInKm.first.id, isChecksd)
        }
    }
}