package com.nayya.workhub.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nayya.workhub.R
import com.nayya.workhub.domain.entity.VacancyEntity

class VacanciesListAdapter(
    private var data: List<VacancyEntity> = mutableListOf()
) : RecyclerView.Adapter<VacanciesListViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun setData(work: List<VacancyEntity>) {
        data = work
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VacanciesListViewHolder {
        return VacanciesListViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.item_value, parent, false
                )
        )
    }

    override fun onBindViewHolder(holder: VacanciesListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private fun getItem(position: Int): VacancyEntity {
        return data[position]
    }

    override fun getItemCount(): Int = data.size
}