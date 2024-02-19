package com.nayya.workhub.ui.job_details

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.nayya.workhub.R
import com.nayya.workhub.databinding.FragmentJobDetailsBinding
import com.nayya.workhub.domain.entity.vacancy.VacancyJobEntity
import com.nayya.workhub.domain.interactor.CollectionVacanciesInteractor
import com.nayya.workhub.ui.root.ViewBindingFragment
import com.nayya.workhub.utils.bpDataFormatter

private const val DETAILS_JPB_KEY = "DETAILS_JPB_KEY"
private const val DAY_IN_MS = 24 * 60 * 60 * 1000L
private const val MESSAGE_KEY = "Отправить вакансию через"

class JobDetailsFragment : ViewBindingFragment<FragmentJobDetailsBinding>(
    FragmentJobDetailsBinding::inflate
) {

    private var flag = true

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

        binding.basicInformationInclude.shareButtonTextView.setOnClickListener {
            sendMessage()
        }
        binding.basicInformationInclude.favoriteButtonTextView.setOnClickListener {
            setColorFavoriteButtonTv()
        }

        VacancyJobParsingText(vacancyJobEntity)

        requirementsParsingText(vacancyJobEntity)

        optionalBenefitsParsingText(vacancyJobEntity)
    }

    @SuppressLint("NewApi")
    private fun requirementsParsingText(vacancy: VacancyJobEntity) {
        val requirementsList =
            vacancy.requirementsEntityList?.first()?.requirements ?: return

        val modificationRequirementsText =
            requirementsList.toString().removeSurrounding("[", "]")

        val spannableStringBuilder = SpannableStringBuilder()

        val modifiedSpannableStringBuilder =
            parsingText(modificationRequirementsText, spannableStringBuilder)

        binding.requirementsInclude.ourRequirementsTextView.setText(
            modifiedSpannableStringBuilder,
            TextView.BufferType.SPANNABLE
        )
    }

    @SuppressLint("NewApi")
    private fun optionalBenefitsParsingText(vacancy: VacancyJobEntity) {
        val optionalBenefitsList =
            vacancy.requirementsEntityList?.first()?.optionalBenefits ?: return

        binding.requirementsInclude.optionalBenefitsLayout.visibility = View.VISIBLE

        val modificationOptionalBenefitsListText =
            optionalBenefitsList.toString().removeSurrounding("[", "]")

        val spannableStringBuilder = SpannableStringBuilder()

        val modifiedSpannableStringBuilder =
            parsingText(modificationOptionalBenefitsListText, spannableStringBuilder)

        binding.requirementsInclude.optionalBenefitsTextView.setText(
            modifiedSpannableStringBuilder,
            TextView.BufferType.SPANNABLE
        )
    }

    @SuppressLint("NewApi")
    private fun VacancyJobParsingText(vacancy: VacancyJobEntity) {
        val fullText = vacancy.dutiesEmployee ?: return

        val spannableStringBuilder = SpannableStringBuilder()

        val modifiedSpannableStringBuilder =
            parsingText(fullText, spannableStringBuilder)

        binding.dutiesEmployeeInclude.dutiesEmployeeTextView.setText(
            modifiedSpannableStringBuilder,
            TextView.BufferType.SPANNABLE
        )
    }

    @SuppressLint("NewApi")
    private fun parsingText(
        fullText: String,
        spannableStringBuilder: SpannableStringBuilder
    ): SpannableStringBuilder {

        for (i in fullText.indices) {
            val currentChar = fullText[i]
            spannableStringBuilder.append(currentChar)

            if (currentChar == '.') {
                spannableStringBuilder.append("\n\n")
            }
        }
        return spannableStringBuilder
    }

    private fun sendMessage() {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, MESSAGE_KEY)
        val chooserIntent = Intent.createChooser(intent, "Отправить вакансию через")
        startActivity(chooserIntent)
    }

    private fun setColorFavoriteButtonTv() {
        val favoriteRedIcon =
            ContextCompat.getDrawable(requireActivity(), R.drawable.ic_emergency_red_24)
        val favoriteGrayIcon =
            ContextCompat.getDrawable(requireActivity(), R.drawable.ic_emergency_24)

        if (!flag) {
            binding.basicInformationInclude.favoriteButtonTextView.setCompoundDrawablesWithIntrinsicBounds(
                null,
                favoriteGrayIcon,
                null,
                null
            )
            binding.basicInformationInclude.favoriteButtonTextView.setTextColor(
                ContextCompat.getColor(
                    requireActivity(),
                    R.color.gray
                )
            )

            flag = true
        } else {
            binding.basicInformationInclude.favoriteButtonTextView.setCompoundDrawablesWithIntrinsicBounds(
                null,
                favoriteRedIcon,
                null,
                null
            )
            binding.basicInformationInclude.favoriteButtonTextView.setTextColor(
                ContextCompat.getColor(
                    requireActivity(),
                    R.color.red
                )
            )
            flag = false
        }
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