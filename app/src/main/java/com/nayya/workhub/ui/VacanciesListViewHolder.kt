package com.nayya.workhub.ui

import android.content.Context
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.nayya.workhub.R
import com.nayya.workhub.domain.entity.vacancy.VacancyJobEntity

class VacanciesListViewHolder(
    initView: View,
    onDetailsJobListener: (VacancyJobEntity) -> Unit,
    val context: Context,
    private val viewModel: VacanciesListViewModel
) : RecyclerView.ViewHolder(initView) {

    private lateinit var jobEntity: VacancyJobEntity
    private var flag = true

    private val titleVacanciesUpkeepTv =
        itemView.findViewById<TextView>(R.id.title_vacancies_upkeep_text_view)
    private val mameCompanyUpkeepTv =
        itemView.findViewById<TextView>(R.id.mame_company_upkeep_text_view)
    private val countryCityUpkeepTv =
        itemView.findViewById<TextView>(R.id.country_city_upkeep_text_view)
    private val contractOptionUpkeepTv =
        itemView.findViewById<TextView>(R.id.contract_option_upkeep_text_view)
    private val financialProposalUpkeepTv =
        itemView.findViewById<TextView>(R.id.financial_proposal_upkeep_text_view)
    private val gettingStartedUpkeepTv =
        itemView.findViewById<TextView>(R.id.getting_started_upkeep_text_view)

    private val closeButtonTv = itemView.findViewById<TextView>(R.id.close_button_text_view)
    private val favoriteButtonTv = itemView.findViewById<TextView>(R.id.favorite_button_text_view)

    fun bind(work: VacancyJobEntity) {
        this.jobEntity = work

        val valueCountry =
            work.countryList.first().country.toString().removeSurrounding("[", "]")

        val valueCity =
            work.cityList.first().city.toString().removeSurrounding("[", "]")

        val valueContractOption =
            work.contractOptionList.first()?.contractOption.toString()
                .removeSurrounding("[", "]")

        val valueFinancialProposal =
            work.financialProposalList.first()?.financialProposal.toString()
                .removeSurrounding("[", "]")

        titleVacanciesUpkeepTv.text = work.titleVacancies
        mameCompanyUpkeepTv.text = work.nameCompany
        countryCityUpkeepTv.text = ("$valueCity, $valueCountry")
        contractOptionUpkeepTv.text = valueContractOption
        financialProposalUpkeepTv.text = valueFinancialProposal
        gettingStartedUpkeepTv.text = work.gettingStarted
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
        viewModel.onDeleteVacancy(jobEntity.key)
    }

    private fun setColorFavoriteButtonTv() {
        val favoriteRedIcon =
            ContextCompat.getDrawable(context, R.drawable.ic_emergency_red_24)
        val favoriteGrayIcon =
            ContextCompat.getDrawable(context, R.drawable.ic_emergency_24)

        if (!flag) {
            favoriteButtonTv.setCompoundDrawablesWithIntrinsicBounds(
                favoriteGrayIcon,
                null,
                null,
                null
            )
            favoriteButtonTv.setTextColor(ContextCompat.getColor(context, R.color.gray))

            flag = true
        } else {
            favoriteButtonTv.setCompoundDrawablesWithIntrinsicBounds(
                favoriteRedIcon,
                null,
                null,
                null
            )
            favoriteButtonTv.setTextColor(ContextCompat.getColor(context, R.color.red))
            flag = false
        }
    }
}
