package com.nayya.workhub.ui

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.nayya.workhub.R
import com.nayya.workhub.domain.entity.offer.OfferListItem
import com.nayya.workhub.utils.image.ImageLoader

class VacanciesListAdapter(
    private var data: List<OfferListItem> = mutableListOf(),
    private var onDetailsJobListener: (OfferListItem) -> Unit = {},
    val context: Context,
    private val imageLoader: ImageLoader<ImageView>,
    private val viewModel: VacanciesListViewModel
) : RecyclerView.Adapter<VacanciesListViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun setData(work: List<OfferListItem>) {
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
            imageLoader,
            viewModel
        )
    }

    override fun onBindViewHolder(holder: VacanciesListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private fun getItem(position: Int): OfferListItem {
        return data[position]
    }

    override fun getItemCount(): Int = data.size
}