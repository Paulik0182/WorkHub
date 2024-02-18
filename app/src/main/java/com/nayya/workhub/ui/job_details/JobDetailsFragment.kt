package com.nayya.workhub.ui.job_details

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.BulletSpan
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

        parsingText4(vacancyJobEntity)
    }

    @SuppressLint("NewApi")
    private fun parsingText(vacancy: VacancyJobEntity) {
        var spannableStringBuilder: SpannableStringBuilder

        // spannableStringBuilder без пересоздания и использования жизненых циклов (работаем с текстом на лету)
        spannableStringBuilder = SpannableStringBuilder(vacancy.dutiesEmployee)
        binding.dutiesEmployeeInclude.dutiesEmployeeTextView.setText(
            spannableStringBuilder,
            TextView.BufferType.EDITABLE
        )
        spannableStringBuilder =
            binding.dutiesEmployeeInclude.dutiesEmployeeTextView.text as SpannableStringBuilder

        if (vacancy.dutiesEmployee != null) {
            for (i in vacancy.dutiesEmployee.indices) {
                if (vacancy.dutiesEmployee[i] == '.') {
                    spannableStringBuilder.replace(i, i, ".\n\n")
                }
            }
        }
    }

    @SuppressLint("NewApi")
    private fun parsingText3(vacancy: VacancyJobEntity) {
        val fullText = vacancy.dutiesEmployee ?: return

        val spannableStringBuilder = SpannableStringBuilder()
        var startIndex = 0

        for (i in fullText.indices) {
            val currentChar = fullText[i]
            spannableStringBuilder.append(currentChar)

            if (currentChar == '.') {
                spannableStringBuilder.append("\n\n")
                startIndex = spannableStringBuilder.length
            } else if (currentChar == ' ' && i > startIndex + 1) {
                val previousChar = fullText[i - 1]
                if (previousChar.isWhitespace() && !previousChar.isWhitespace()) {
                    spannableStringBuilder.setSpan(
                        BulletSpan(),
                        startIndex,
                        startIndex + 1,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                }
            }
        }

        binding.dutiesEmployeeInclude.dutiesEmployeeTextView.setText(
            spannableStringBuilder,
            TextView.BufferType.SPANNABLE
        )
    }

    @SuppressLint("NewApi")
    private fun parsingText4(vacancy: VacancyJobEntity) {
        val fullText = vacancy.dutiesEmployee ?: return

        val spannableStringBuilder = SpannableStringBuilder()

        for (i in fullText.indices) {
            val currentChar = fullText[i]
            spannableStringBuilder.append(currentChar)

            if (currentChar == '.') {
                spannableStringBuilder.append("\n\n")
            }
        }
        binding.dutiesEmployeeInclude.dutiesEmployeeTextView.setText(
            spannableStringBuilder,
            TextView.BufferType.SPANNABLE
        )
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