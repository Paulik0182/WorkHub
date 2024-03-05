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
import com.nayya.workhub.domain.entity.offer.OfferJob
import com.nayya.workhub.domain.entity.offer.repo.PracujPlOfferVacancyRepo
import com.nayya.workhub.domain.entity.offer.vacansy_dto.VacancyHeadingEntity
import com.nayya.workhub.domain.entity.offer.vacansy_dto.addition.VacancyAdditionEntity
import com.nayya.workhub.domain.entity.vacancy.VacancyJobEntity
import com.nayya.workhub.ui.root.ViewBindingFragment
import com.nayya.workhub.utils.image.GlideImageLoader

private const val DETAILS_JPB_KEY = "DETAILS_JPB_KEY"
private const val DAY_IN_MS = 24 * 60 * 60 * 1000L
private const val MESSAGE_KEY = "Отправить вакансию через"

class JobDetailsFragment : ViewBindingFragment<FragmentJobDetailsBinding>(
    FragmentJobDetailsBinding::inflate
) {

    private val pracujPlOfferVacancyRepo: PracujPlOfferVacancyRepo by lazy {
        app.pracujPlOfferVacancyRepo
    }

//    private val collectionVacanciesInteractor: CollectionVacanciesInteractor by lazy {
//        app.collectionVacanciesInteractor
//    }
//    private val favoriteCollectionVacanciesJobInteractor:
//            FavoriteCollectionVacanciesJobInteractor by lazy {
//        app.favoriteCollectionVacanciesJobInteractor
//    }
//
//
//    private val offerFavoriteRepo: OfferFavoriteRepo by lazy {
//        app.myDiy.offerFavoriteRepo
//    }

    private val viewModel: JobDetailsViewModel by lazy {
        ViewModelProvider(
            this,
            JobDetailsViewModel.Factory(
                pracujPlOfferVacancyRepo = pracujPlOfferVacancyRepo,
                offerListItem = arguments?.getParcelable(DETAILS_JPB_KEY) as? OfferJob
            )
        )[JobDetailsViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.vacancyJobHeadingLiveData.observe(viewLifecycleOwner) {
            setJob(it)
        }

        viewModel.vacancyJobAdditionLiveData.observe(viewLifecycleOwner) {
            setJobAddition(it)
        }

    }

    private fun setJobAddition(vacancyAdditionEntity: VacancyAdditionEntity) {

        val labelCompany = vacancyAdditionEntity.offerReducer?.offer?.style?.logo

        labelCompany?.let {
            GlideImageLoader().loadInto(it, binding.basicInformationInclude.labelCompanyImage)
        }

        binding.basicInformationInclude.financialProposalTextView.text =
            getSalaryInfoString(vacancyAdditionEntity)

        binding.basicInformationInclude.countryCityTextView.text =
            getAddressString(vacancyAdditionEntity)

        binding.basicInformationInclude.employmentRateTextView.text =
            vacancyAdditionEntity.offerReducer?.offer?.workSchedules

        binding.basicInformationInclude.specialistLevelTextView.text =
            vacancyAdditionEntity.offerReducer?.offer?.positionLevelsName

        binding.basicInformationInclude.typeWorkTextView.text =
            getTypeWorkString(vacancyAdditionEntity)

        binding.basicInformationInclude.gettingStartedTextView.text =
            getStartedString(vacancyAdditionEntity)
    }

    /** Преобразуем строку о найме (кого или/и когда нанимают) */
    private fun getStartedString(vacancyAdditionEntity: VacancyAdditionEntity): String {
        return vacancyAdditionEntity.offerReducer?.offer?.primaryAttributes?.mapNotNull {
            it.label?.primaryTargetSiteText
        }?.joinToString("\n") ?: ""
    }

    /** Преобразуем строку о типе работы */
    private fun getTypeWorkString(vacancyAdditionEntity: VacancyAdditionEntity): String {
        return vacancyAdditionEntity.offerReducer?.offer?.workModes?.mapNotNull { it.text }
            ?.joinToString("\n") ?: ""
    }

    /** Собираем строку по финансовому предложению (зарплата) */
    private fun getSalaryInfoString(vacancyAdditionEntity: VacancyAdditionEntity): String {
        val salaryList =
            vacancyAdditionEntity.offerReducer?.offer?.typesOfContracts?.mapNotNull { it.salary }
        val offerTitleList =
            vacancyAdditionEntity.offerReducer?.offer?.typesOfContracts?.mapNotNull { it.name }

        val salaryInfoList = salaryList?.mapIndexedNotNull { index, salary ->
            val from = salary.from?.let {
                String.format("%,d", it).replace(",", " ")
            }
            val to = salary.to?.let {
                String.format("%,d", it).replace(",", " ")
            }
            val currency = salary.currency?.symbol
            val timeUnit = salary.timeUnit?.shortForm?.name
            val salaryKind = salary.salaryKind?.name

            if (from != null && to != null) {
                "$from - $to $currency. $salaryKind / $timeUnit"
            } else {
                null
            }
        }

        /**  "zip" (соединение) между списками */
        val combinedList =
            offerTitleList?.zip(salaryInfoList ?: emptyList())

        val salaryInfo =
            combinedList?.joinToString("\n") { (offerTitle, salaryInfo) ->
                "$offerTitle - $salaryInfo"
            } ?: ""

        return salaryInfo
    }

    /** Собираем строку по месту нахождению (Страна, регион, город) */
    private fun getAddressString(vacancyAdditionEntity: VacancyAdditionEntity): String {
        val countryTitle = vacancyAdditionEntity.offerReducer?.offer?.workplaces
            ?.mapNotNull { it.country?.name }
            ?.joinToString(", ")
            ?.takeIf { it.isNotBlank() }

        val regionTitle = vacancyAdditionEntity.offerReducer?.offer?.workplaces
            ?.mapNotNull { it.region?.pracujPlName }
            ?.joinToString(", ")
            ?.takeIf { it.isNotBlank() }

        val cityTitle = vacancyAdditionEntity.offerReducer?.offer?.workplaces
            ?.mapNotNull { it.inlandLocation?.location?.name }
            ?.joinToString(", ")
            ?.takeIf { it.isNotBlank() }

        val district = vacancyAdditionEntity.offerReducer?.offer?.workplaces
            ?.mapNotNull { it.inlandLocation?.address?.district }
            ?.joinToString(", ")
            ?.takeIf { it.isNotBlank() }

        return listOfNotNull(countryTitle, regionTitle, cityTitle, district)
            .joinToString(", ")
    }


    @SuppressLint("SetTextI18n")
    private fun setJob(vacancyHeadingEntity: VacancyHeadingEntity) {

//        setColorFavoriteButtonTv(vacancyJobDto.isFavorite)

//        val valueFinancialProposal = vacancyJobEntity.financialProposalList
//            ?.first()?.financialProposal.toString().removeSurrounding("[", "]")
//            .split(", ") // Разделение значений списка
//            .joinToString(separator = "\n") // Объединение значений с новой строки
//
//        val valueCountry =
//            vacancyJobEntity.countryList.first().country.toString().removeSurrounding(
//                "[", "]"
//            )
//
//        val valueCity =
//            vacancyJobEntity.cityList.first().city.toString().removeSurrounding(
//                "[", "]"
//            )
//
//        val dateEnd =
//            bpDataFormatter.format(
//                (DAY_IN_MS * vacancyJobEntity.offerValid) + vacancyJobEntity.deadlineEndOffer
//            )
//
//        val contractOption =
//            vacancyJobEntity.contractOptionList?.first()?.contractOption.toString()
//                .removeSurrounding("[", "]")
//
//        val offerValid = vacancyJobEntity.offerValid

        binding.basicInformationInclude.titleVacanciesTextView.text =
            vacancyHeadingEntity.title

        binding.basicInformationInclude.nameCompanyTextView.text =
            vacancyHeadingEntity.hiringOrganization
//        binding.basicInformationInclude.financialProposalTextView.text = valueFinancialProposal
//        binding.basicInformationInclude.countryCityTextView.text = ("$valueCity, $valueCountry")
//        binding.basicInformationInclude.informationAboutEndOfferTextView.text =
//            "Really: $offerValid day"
//
//        binding.basicInformationInclude.informationAboutEndOfferDateTextView.text = "Do: $dateEnd"
//        binding.basicInformationInclude.contractOptionTextView.text = contractOption
//        binding.basicInformationInclude.employmentRateTextView.text =
//            vacancyJobEntity.employmentRate
//
//        binding.basicInformationInclude.specialistLevelTextView.text =
//            vacancyJobEntity.specialistLevel
//
//        binding.basicInformationInclude.typeWorkTextView.text = vacancyJobEntity.typeWork
//        binding.basicInformationInclude.gettingStartedTextView.text =
//            vacancyJobEntity.gettingStarted
//
//        binding.basicInformationInclude.informationAboutInterviewTextView.text =
//            vacancyJobEntity.interviewMethod
//
//        binding.basicInformationInclude.shareButtonTextView.setOnClickListener {
//            sendMessage()
//        }
//        binding.basicInformationInclude.favoriteButtonTextView.setOnClickListener {
//            viewModel.onFavoriteChange(vacancyJobEntity)
//            setColorFavoriteButtonTv(!vacancyJobEntity.isFavorite)
//        }
//
//        VacancyJobParsingText(vacancyJobEntity)
//
//        requirementsParsingText(vacancyJobEntity)
//
//        optionalBenefitsParsingText(vacancyJobEntity)
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

    private fun setColorFavoriteButtonTv(isFavorite: Boolean) {
        val favoriteRedIcon =
            ContextCompat.getDrawable(requireActivity(), R.drawable.ic_emergency_red_24)
        val favoriteGrayIcon =
            ContextCompat.getDrawable(requireActivity(), R.drawable.ic_emergency_24)

        if (!isFavorite) {
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
        fun newInstance(offerListItem: OfferJob?) =
            JobDetailsFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(DETAILS_JPB_KEY, offerListItem)
                }
            }
    }
}