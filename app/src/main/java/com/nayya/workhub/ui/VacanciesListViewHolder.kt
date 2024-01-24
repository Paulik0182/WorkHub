package com.nayya.workhub.ui

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nayya.workhub.R
import com.nayya.workhub.domain.entity.VacancyEntity

class VacanciesListViewHolder(
    initView: View
) : RecyclerView.ViewHolder(initView) {

    private val valueTv = itemView.findViewById<TextView>(R.id.value_text_view)

    fun bind(work: VacancyEntity) {
        valueTv.text = work.vacancy
    }
}
