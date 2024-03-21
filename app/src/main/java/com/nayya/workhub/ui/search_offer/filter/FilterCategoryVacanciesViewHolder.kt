package com.nayya.workhub.ui.search_offer.filter

import android.content.Context
import android.graphics.Typeface
import android.util.DisplayMetrics
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import com.nayya.workhub.R
import com.nayya.workhub.domain.entity.filter_category.CategoryVacanciesEntity

class FilterCategoryVacanciesViewHolder(
    initView: View,
    var context: Context,
    val listener: (String, Boolean) -> Unit
) : RecyclerView.ViewHolder(initView) {

    private val checkBox = initView.findViewById<CheckBox>(R.id.check_box)

    fun bind(categoryVacancies: Pair<CategoryVacanciesEntity, Boolean>) {
        val category = categoryVacancies.first

        checkBox.text = category.categoryName
        checkBox.setOnCheckedChangeListener(null)
        checkBox.isChecked = categoryVacancies.second

        val params: ViewGroup.MarginLayoutParams =
            checkBox.layoutParams as ViewGroup.MarginLayoutParams

        if (category.level == 1 && category.parentId == null) {
            checkBox.visibility = View.VISIBLE
            checkBox.typeface = Typeface.DEFAULT_BOLD
            params.setMargins(0, 0, 0, 0)
            checkBox.layoutParams = params
        } else if (category.parentId != null && category.level == 2) {
            checkBox.visibility = View.VISIBLE
            checkBox.typeface = Typeface.DEFAULT
            params.setMargins(convertDpToPixel(25f).toInt(), 0, 0, 0)
            checkBox.layoutParams = params
        } else {
            checkBox.visibility = View.GONE
        }

        checkBox.setOnCheckedChangeListener { _, isChecked ->
            listener(category.id.toString(), isChecked)
        }
    }

    private fun convertDpToPixel(dp: Float): Float {
        return dp * (context.resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
    }
}