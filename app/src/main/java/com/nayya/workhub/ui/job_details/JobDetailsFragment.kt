package com.nayya.workhub.ui.job_details

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.nayya.workhub.databinding.FragmentJobDetailsBinding
import com.nayya.workhub.domain.entity.vacancy.VacancyJobEntity
import com.nayya.workhub.domain.interactor.CollectionVacanciesInteractor
import com.nayya.workhub.ui.root.ViewBindingFragment
import com.nayya.workhub.utils.bpDataFormatter

private const val DETAILS_JPB_KEY = "DETAILS_JPB_KEY"
private const val DAY_IN_MS = 24 * 60 * 60 * 1000L

class JobDetailsFragment : ViewBindingFragment<FragmentJobDetailsBinding>(
    FragmentJobDetailsBinding::inflate
) {

    private val collectionVacanciesInteractor: CollectionVacanciesInteractor by lazy {
        app.collectionVacanciesInteractor
    }

    private val viewModel: JobDetailsViewModel by lazy {
        ViewModelProvider(
            this,
            JobDetailsViewModel.Factory(
                collectionVacanciesInteractor,
                requireArguments().getString(DETAILS_JPB_KEY)!!
            )
        )[JobDetailsViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.vacancyJobLiveData.observe(viewLifecycleOwner) {
            setJob(it)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setJob(vacancyJobEntity: VacancyJobEntity) {

        val valueFinancialProposal = vacancyJobEntity.financialProposalList
            .first()?.financialProposal.toString().removeSurrounding("[", "]")
            .split(", ") // Разделение значений списка
            .joinToString(separator = "\n") // Объединение значений с новой строки

        val valueCountry =
            vacancyJobEntity.countryList.first().country.toString().removeSurrounding(
                "[", "]"
            )

        val valueCity =
            vacancyJobEntity.cityList.first().city.toString().removeSurrounding(
                "[", "]"
            )

        val dateEnd =
            bpDataFormatter.format(
                (DAY_IN_MS * vacancyJobEntity.offerValid) + vacancyJobEntity.deadlineEndOffer
            )

        val contractOption =
            vacancyJobEntity.contractOptionList.first()?.contractOption.toString()
                .removeSurrounding("[", "]")

        val offerValid = vacancyJobEntity.offerValid

        binding.basicInformationInclude.titleVacanciesTextView.text =
            vacancyJobEntity.titleVacancies

        binding.basicInformationInclude.nameCompanyTextView.text = vacancyJobEntity.nameCompany
        binding.basicInformationInclude.financialProposalTextView.text = valueFinancialProposal
        binding.basicInformationInclude.countryCityTextView.text = ("$valueCity, $valueCountry")
        binding.basicInformationInclude.informationAboutEndOfferTextView.text =
            "Really: $offerValid day"

        binding.basicInformationInclude.informationAboutEndOfferDateTextView.text = "Do: $dateEnd"
        binding.basicInformationInclude.contractOptionTextView.text = contractOption
        binding.basicInformationInclude.employmentRateTextView.text =
            vacancyJobEntity.employmentRate

        binding.basicInformationInclude.specialistLevelTextView.text =
            vacancyJobEntity.specialistLevel

        binding.basicInformationInclude.typeWorkTextView.text = vacancyJobEntity.typeWork
        binding.basicInformationInclude.gettingStartedTextView.text =
            vacancyJobEntity.gettingStarted

        binding.basicInformationInclude.informationAboutInterviewTextView.text =
            vacancyJobEntity.interviewMethod
    }

    private fun getController(): Controller = activity as Controller

    interface Controller {
        //todo
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        getController()
    }

    companion object {

        @JvmStatic
        fun newInstance(jobId: String) =
            JobDetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(DETAILS_JPB_KEY, jobId)

                }
            }
    }
}