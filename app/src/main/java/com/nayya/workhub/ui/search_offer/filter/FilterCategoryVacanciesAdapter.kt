package com.nayya.workhub.ui.search_offer.filter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nayya.workhub.R
import com.nayya.workhub.domain.entity.filter_category.CategoryVacanciesEntity

class FilterCategoryVacanciesAdapter(
    private var data: List<Pair<CategoryVacanciesEntity, Boolean>> = mutableListOf()
) : RecyclerView.Adapter<FilterCategoryVacanciesViewHolder>() {

    fun getSelectedCategories(): List<Pair<CategoryVacanciesEntity, Boolean>> {
        return data
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(categoryVacancies: List<Pair<CategoryVacanciesEntity, Boolean>>) {
        data = categoryVacancies
        notifyDataSetChanged()
//        notifyItemChanged()
//        notifyItemRangeChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FilterCategoryVacanciesViewHolder {
        return FilterCategoryVacanciesViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.item_check_box, parent, false
                )
        )
    }

    override fun onBindViewHolder(holder: FilterCategoryVacanciesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemCount(): Int = data.size

    private fun getItem(position: Int): Pair<CategoryVacanciesEntity, Boolean> {
        return data[position]
    }
}