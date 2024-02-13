package com.nayya.workhub.ui

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nayya.workhub.R
import com.nayya.workhub.domain.entity.vacancy.VacancyJobEntity

class VacanciesListViewHolder(
    initView: View,
    onDetailsJobListener: (VacancyJobEntity) -> Unit
) : RecyclerView.ViewHolder(initView) {

    private lateinit var jobEntity: VacancyJobEntity

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
    }
}
