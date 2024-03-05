package com.nayya.workhub.ui.job_details.city_for_work

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nayya.workhub.R
import com.nayya.workhub.domain.entity.offer.OfferJob

class CityForWorkAdapter(
    private var data: List<OfferJob> = mutableListOf(),
    private var onDetailsJobListener: (OfferJob) -> Unit
) : RecyclerView.Adapter<CityForWorkListViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun setData(city: List<OfferJob>) {
        data = city
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityForWorkListViewHolder {
        return CityForWorkListViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.item_city_for_work, parent, false
                ),
            onDetailsJobListener
        )
    }

    override fun onBindViewHolder(holder: CityForWorkListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private fun getItem(position: Int): OfferJob {
        return data[position]
    }

    override fun getItemCount(): Int = data.size
}

class CityForWorkListViewHolder(
    initView: View,
    onDetailsJobListener: (OfferJob) -> Unit
) : RecyclerView.ViewHolder(initView) {

    private lateinit var cityListItem: OfferJob

    private val cityForWorkTv = itemView.findViewById<TextView>(R.id.city_for_work_text_view)
    fun bind(city: OfferJob) {
        this.cityListItem = city

        cityForWorkTv.text = city.displayWorkplace
    }

    init {
        initView.setOnClickListener {
            onDetailsJobListener.invoke(cityListItem)
        }
    }
}