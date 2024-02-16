package com.nayya.workhub.ui

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nayya.workhub.R
import com.nayya.workhub.domain.entity.vacancy.VacancyJobEntity

class VacanciesListAdapter(
    private var data: List<VacancyJobEntity> = mutableListOf(),
    private var onDetailsJobListener: (VacancyJobEntity) -> Unit = {},
    val context: Context,
    private val viewModel: VacanciesListViewModel
) : RecyclerView.Adapter<VacanciesListViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun setData(work: List<VacancyJobEntity>) {
        data = work
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VacanciesListViewHolder {
        return VacanciesListViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.item_value2, parent, false
                ),
            onDetailsJobListener,
            context,
            viewModel
        )
    }

    override fun onBindViewHolder(holder: VacanciesListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private fun getItem(position: Int): VacancyJobEntity {
        return data[position]
    }

    override fun getItemCount(): Int = data.size
}