package com.nayya.workhub.ui

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.nayya.workhub.R
import com.nayya.workhub.domain.entity.offer.OfferListItem
import com.nayya.workhub.utils.image.ImageLoader
import com.nayya.workhub.utils.toFormattedString

class VacanciesListViewHolder(
    initView: View,
    onDetailsJobListener: (OfferListItem) -> Unit,
    val context: Context,
    private val imageLoader: ImageLoader<ImageView>,
    private val viewModel: VacanciesListViewModel
) : RecyclerView.ViewHolder(initView) {

    private lateinit var jobEntity: OfferListItem
    private val valueNull = ""

    private val titleVacanciesUpkeepTv =
        itemView.findViewById<TextView>(R.id.title_vacancies_upkeep_text_view)

    private val mameCompanyUpkeepTv =
        itemView.findViewById<TextView>(R.id.mame_company_upkeep_text_view)

    private val countryCityUpkeepTv =
        itemView.findViewById<TextView>(R.id.country_city_upkeep_text_view)

    private val contractOptionUpkeepTv =
        itemView.findViewById<TextView>(R.id.contract_option_upkeep_text_view)
    private val contractOptionUpkeepLayout: View =
        itemView.findViewById(R.id.contract_option_upkeep_layout)

    private val financialProposalUpkeepTv =
        itemView.findViewById<TextView>(R.id.financial_proposal_upkeep_text_view)
    private val financialProposalUpkeepLayout: View =
        itemView.findViewById(R.id.financial_proposal_upkeep_layout)

    private val gettingStartedUpkeepTv =
        itemView.findViewById<TextView>(R.id.getting_started_upkeep_text_view)

    private val workSchedulesTv =
        itemView.findViewById<TextView>(R.id.work_schedules_text_view)
    private val workSchedulesLayout: View =
        itemView.findViewById(R.id.work_schedules_layout)

    private val workModesTv =
        itemView.findViewById<TextView>(R.id.work_modes_text_view)
    private val workModesLayout: View =
        itemView.findViewById(R.id.work_modes_layout)

    private val positionLevelsTv =
        itemView.findViewById<TextView>(R.id.position_levels_text_view)
    private val positionLevelsLayout: View =
        itemView.findViewById<TextView>(R.id.position_levels_layout)

    private val labelCompanyUpkeepImage =
        itemView.findViewById<ImageView>(R.id.label_company_upkeep_image)

    private val closeButtonTv = itemView.findViewById<TextView>(R.id.close_button_text_view)
    private val favoriteButtonTv = itemView.findViewById<TextView>(R.id.favorite_button_text_view)

    fun bind(work: OfferListItem) {
        this.jobEntity = work

        val labelCompany = work.companyLogoUri

        val valueCity = work.offers?.map {
            it.displayWorkplace
        }?.toFormattedString()

        val valueContractOption = work.typesOfContract?.toFormattedString()

        val workSchedules = work.workSchedules?.toFormattedString()

        val workModes = work.workModes?.toFormattedString()

        val positionLevels = work.positionLevels?.toFormattedString()

        labelCompany?.let {
            imageLoader.loadInto(it, labelCompanyUpkeepImage)
        }

        mameCompanyUpkeepTv.text = work.companyName
        titleVacanciesUpkeepTv.text = work.jobTitle
        countryCityUpkeepTv.text = valueCity

        if (valueContractOption !in listOf(null, valueNull)) {
            contractOptionUpkeepTv.text = valueContractOption
        } else {
            contractOptionUpkeepLayout.visibility = View.GONE
        }

        if (work.salaryDisplayText !in listOf(null, valueNull)) {
            financialProposalUpkeepTv.text = work.salaryDisplayText
        } else {
            financialProposalUpkeepLayout.visibility = View.GONE
        }

        if (workSchedules !in listOf(null, valueNull)) {
            workSchedulesTv.text = workSchedules
        } else {
            workSchedulesLayout.visibility = View.GONE
        }

        if (workModes !in listOf(null, valueNull)) {
            workModesTv.text = workModes
        } else {
            workModesLayout.visibility = View.GONE
        }

        if (positionLevels !in listOf(null, valueNull)) {
            positionLevelsTv.text = positionLevels
        } else {
            positionLevelsLayout.visibility = View.GONE
        }
    }

    init {
        initView.setOnClickListener {
            onDetailsJobListener.invoke(jobEntity)
        }

        closeButtonTv.setOnClickListener {
            onDeleteClick()
        }

        favoriteButtonTv.setOnClickListener {
            setColorFavoriteButtonTv()
            Toast.makeText(context, "Действие еще не описано", Toast.LENGTH_SHORT).show()
        }
    }

    private fun onDeleteClick() {
//        viewModel.onDeleteVacancy(jobEntity.key)
    }

    private fun setColorFavoriteButtonTv() {
        val favoriteRedIcon =
            ContextCompat.getDrawable(context, R.drawable.ic_emergency_red_24)
        val favoriteGrayIcon =
            ContextCompat.getDrawable(context, R.drawable.ic_emergency_24)

        if (!jobEntity.isFavorite) {
            favoriteButtonTv.setCompoundDrawablesWithIntrinsicBounds(
                favoriteGrayIcon,
                null,
                null,
                null
            )
            favoriteButtonTv.setTextColor(ContextCompat.getColor(context, R.color.gray))

            jobEntity.isFavorite = true
        } else {
            favoriteButtonTv.setCompoundDrawablesWithIntrinsicBounds(
                favoriteRedIcon,
                null,
                null,
                null
            )
            favoriteButtonTv.setTextColor(ContextCompat.getColor(context, R.color.red))
            jobEntity.isFavorite = false
        }
    }
}
