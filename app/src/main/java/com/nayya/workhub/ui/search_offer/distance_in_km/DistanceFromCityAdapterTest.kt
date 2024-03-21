package com.nayya.workhub.ui.search_offer.distance_in_km

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import com.nayya.workhub.R
import com.nayya.workhub.domain.entity.filter_category.DistanceInKmEntity

class DistanceFromCityAdapterTest(
    private var data: List<DistanceInKmEntity> = mutableListOf(),
    private val onClickDistanceListener: (String) -> Unit,
) : RecyclerView.Adapter<DistanceFromCityViewHolderTest>() {

    var selectedPosition: Int = RecyclerView.NO_POSITION
    var checkedId: String? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setData(distance: List<DistanceInKmEntity>) {
        data = distance
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setSelection(selectedId: String) {
        checkedId = selectedId
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DistanceFromCityViewHolderTest {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_check_box, parent, false)
        val viewHolder = DistanceFromCityViewHolderTest(itemView, onClickDistanceListener)
        viewHolder.bindData(this)
        return viewHolder
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: DistanceFromCityViewHolderTest, position: Int) {
        holder.bind(data[position], position)
        holder.itemView.setOnClickListener {
            onCheckBoxClicked(holder.adapterPosition)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun onCheckBoxClicked(position: Int) {
        if (position == selectedPosition) {
            return
        }

        val previousPosition = selectedPosition
        selectedPosition = position

        notifyItemChanged(previousPosition)
        notifyItemChanged(selectedPosition)

        checkedId = data[selectedPosition].id
        onClickDistanceListener.invoke(data[selectedPosition].id)

        notifyDataSetChanged()
    }
}

class DistanceFromCityViewHolderTest(
    initView: View,
    private val onClickDistanceListener: (String) -> Unit,
) : RecyclerView.ViewHolder(initView) {

    private val checkBox = initView.findViewById<CheckBox>(R.id.check_box)
    private lateinit var adapter: DistanceFromCityAdapterTest

    fun bindData(adapter: DistanceFromCityAdapterTest) {
        this.adapter = adapter
    }

    fun bind(data: DistanceInKmEntity, position: Int) {
        checkBox.text = data.distanceFromCityInKm.toString()
        checkBox.isChecked = data.id == adapter.checkedId

        itemView.setOnClickListener {
            onClickDistanceListener.invoke(data.id)
            adapter.onCheckBoxClicked(position)
        }

        checkBox.setOnClickListener {
            adapter.onCheckBoxClicked(position)
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