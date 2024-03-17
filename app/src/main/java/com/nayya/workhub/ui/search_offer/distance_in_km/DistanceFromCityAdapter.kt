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

    var selectedPosition: Int = RecyclerView.NO_POSITION
    var checkedId: String? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DistanceFromCityViewHolder {
        val viewHolder = DistanceFromCityViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.item_check_box, parent, false
                ),
            onClickDistanceListener
        )
        viewHolder.bindData(this)
        return viewHolder
    }

    override fun onBindViewHolder(holder: DistanceFromCityViewHolder, position: Int) {
        holder.bind(getItem(position), position)
        holder.itemView.setOnClickListener {
            onCheckBoxClicked(holder.adapterPosition)
        }
    }

    private fun getItem(position: Int): Pair<DistanceInKmEntity, Boolean> {
        return data[position]
    }

    override fun getItemCount(): Int = data.size

    fun onCheckBoxClicked(position: Int) {
        if (position == selectedPosition) {
            return
        }

        val previousPosition = selectedPosition
        selectedPosition = position

        notifyItemChanged(previousPosition)
        notifyItemChanged(selectedPosition)

        checkedId = data[selectedPosition].first.id
//        onClickDistanceListener(data[selectedPosition].first.id, false)

        notifyDataSetChanged()
    }
}

class DistanceFromCityViewHolder(
    initView: View,
    private val onClickDistanceListener: (String, Boolean) -> Unit
) : RecyclerView.ViewHolder(initView) {

    private val checkBox = initView.findViewById<CheckBox>(R.id.check_box)
    private lateinit var adapter: DistanceFromCityAdapter

    fun bindData(adapter: DistanceFromCityAdapter) {
        this.adapter = adapter
    }

    fun bind(distanceInKm: Pair<DistanceInKmEntity, Boolean>, position: Int) {
        checkBox.text = distanceInKm.first.distanceFromCityInKm.toString()
        checkBox.setOnCheckedChangeListener(null)
        checkBox.isChecked = distanceInKm.second


        checkBox.setOnCheckedChangeListener { _, isChecksd ->
            onClickDistanceListener(distanceInKm.first.id, isChecksd)
//            adapter.onCheckBoxClicked(position)
        }

        if (position == adapter.selectedPosition) {
            selectCheckBox()
        } else {
            deselectCheckBox()
        }
    }

    private fun selectCheckBox() {
        itemView.isSelected = true
    }

    private fun deselectCheckBox() {
        itemView.isSelected = false
    }
}