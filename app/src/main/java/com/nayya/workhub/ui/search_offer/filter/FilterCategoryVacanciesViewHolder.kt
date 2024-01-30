package com.nayya.workhub.ui.search_offer.filter

import android.view.View
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import com.nayya.workhub.R
import com.nayya.workhub.domain.entity.filter_category.CategoryVacanciesEntity

class FilterCategoryVacanciesViewHolder(
    initView: View
) : RecyclerView.ViewHolder(initView) {

    private val checkBox = initView.findViewById<CheckBox>(R.id.check_box)

    fun bind(categoryVacancies: Pair<CategoryVacanciesEntity, Boolean>) {
        checkBox.text = categoryVacancies.first.categoryName
        checkBox.isChecked = categoryVacancies.second

//        checkBox.setOnCheckedChangeListener { _, isChecked ->
//            categoryVacancies.isChecked = isChecked
//        }
    }
}