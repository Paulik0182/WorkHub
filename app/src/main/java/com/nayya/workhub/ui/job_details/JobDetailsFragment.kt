package com.nayya.workhub.ui.job_details

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.util.Log
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.google.android.flexbox.FlexboxLayout
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.nayya.workhub.R
import com.nayya.workhub.databinding.FragmentJobDetailsBinding
import com.nayya.workhub.domain.entity.offer.OfferJob
import com.nayya.workhub.domain.entity.offer.repo.PracujPlOfferVacancyRepo
import com.nayya.workhub.domain.entity.offer.vacansy_dto.VacancyHeadingEntity
import com.nayya.workhub.domain.entity.offer.vacansy_dto.addition.VacancyAdditionEntity
import com.nayya.workhub.domain.entity.vacancy.VacancyJobEntity
import com.nayya.workhub.ui.root.ViewBindingFragment
import com.nayya.workhub.utils.image.GlideImageLoader
import com.nayya.workhub.utils.toFormattedString

private const val DETAILS_JPB_KEY = "DETAILS_JPB_KEY"
private const val DAY_IN_MS = 24 * 60 * 60 * 1000L
private const val MESSAGE_KEY = "Отправить вакансию через"

class JobDetailsFragment : ViewBindingFragment<FragmentJobDetailsBinding>(
    FragmentJobDetailsBinding::inflate
), OnMapReadyCallback {

    private val valueNull = ""

    private lateinit var googleMap: GoogleMap

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
        enableLocation()
//        val mapFragment =
//            childFragmentManager.findFragmentById(R.id.map_fragment) as SupportMapFragment
//
//        mapFragment.getMapAsync(this)

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

//        if (getResponsibilitiesString(vacancyAdditionEntity).second !in listOf(null, valueNull)) {
        binding.dutiesEmployeeInclude.titleDutiesEmployeeTextView.text =
            getResponsibilitiesString(
                vacancyAdditionEntity,
                "responsibilities"
            ).first
        binding.dutiesEmployeeInclude.dutiesEmployeeTextView.text =
            getResponsibilitiesString(
                vacancyAdditionEntity,
                "responsibilities",
                "@"
            ).second
//        } else {
//            binding.dutiesEmployeeLayout.visibility = View.GONE
//        }

//        if (getRequirementsString(vacancyAdditionEntity).second !in listOf(null, valueNull)) {
        binding.requirementsInclude.titleRequirementsTextView.text =
            getRequirementsString(vacancyAdditionEntity).first

        binding.requirementsInclude.ourRequirementsTextView.text =
            getRequirementsString(vacancyAdditionEntity).second
//        } else {
//            binding.requirementsLayout.visibility = View.GONE
//        }

//        if (getRequirementsOptionalString(vacancyAdditionEntity).second !in listOf(
//                null,
//                valueNull
//            )
//        ) {
        binding.requirementsInclude.titleOptionalBenefitsTextView.text =
            getRequirementsOptionalString(vacancyAdditionEntity).first

        binding.requirementsInclude.optionalBenefitsTextView.text =
            getRequirementsOptionalString(vacancyAdditionEntity).second
//        } else {
//            binding.requirementsInclude.optionalBenefitsLayout.visibility = View.GONE
//        }

        binding.aboutProjectInclude.titleAboutProjectTextView.text =
            getResponsibilitiesString(
                vacancyAdditionEntity,
                "about-project"
            ).first
        binding.aboutProjectInclude.aboutProjectTextView.text =
            getResponsibilitiesString(
                vacancyAdditionEntity,
                "about-project"
            ).second

        // для разметки Технологии
        binding.technologiesInclude.titleTechnologiesTextView.text =
            getTechnologiesString(vacancyAdditionEntity, "technologies").first[0]
        binding.technologiesInclude.titleTechnologiesExpectedTextView.text =
            getTechnologiesString(vacancyAdditionEntity, "technologies-expected").first[1]
        binding.technologiesInclude.titleTechnologiesOptionalTextView.text =
            getTechnologiesString(vacancyAdditionEntity, "technologies-optional").first[1]
        generateTextViewTechnologiesAndSetValues(
            binding.technologiesInclude.technologiesExpectedContainer as FlexboxLayout,
            vacancyAdditionEntity,
            "technologies-expected"
        )
        generateTextViewTechnologiesAndSetValues(
            binding.technologiesInclude.technologiesOptionalContainer as FlexboxLayout,
            vacancyAdditionEntity,
            "technologies-optional"
        )

        setGoogleMap(
            getAbroadAddress(vacancyAdditionEntity).first,
            getAbroadAddress(vacancyAdditionEntity).second,
        )
    }

    /** Преобразуем строку Требования */
    private fun getAbroadAddress(
        vacancyAdditionEntity: VacancyAdditionEntity
    ): Pair<String, LatLng?> {

        var addressString: String = ""
        var coordinates: LatLng? = null

        vacancyAdditionEntity.offerReducer?.offer?.workplaces?.mapNotNull { addressCoordinates ->
            val country = addressCoordinates.country?.name
            val region = ", " + addressCoordinates.region?.pracujPlName
            val city = ", " + addressCoordinates.inlandLocation?.location?.name
            val basicAddress = addressCoordinates.inlandLocation?.address
            val zipCode = ", " + basicAddress?.zipCode
            val street = ", " + basicAddress?.street
            val buildingNumber = ", " + basicAddress?.buildingNumber
            val flatNumber = ", " + basicAddress?.flatNumber
            val additionalInfo = ", " + basicAddress?.additionalInfo
            val coordinatesLatitude = basicAddress?.coordinates?.latitude ?: 0.0
            val coordinatesLongitude = basicAddress?.coordinates?.longitude ?: 0.0

            addressString =
                "$country$region$city$zipCode$street$buildingNumber$flatNumber$additionalInfo"
            coordinates = LatLng(coordinatesLatitude, coordinatesLongitude)
        }?.toFormattedString() ?: ""

        return Pair(addressString, coordinates)
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map
    }

    @SuppressLint("MissingPermission")
    private fun setGoogleMap(
        address: String,
        coordinates: LatLng?
    ) {

        binding.googleMapInclude.titleAddressTextView.text = address
//        val isParis = LatLng(48.85769609115522, 2.3638554986231695)
        var toMarker: MarkerOptions? = null
        val mapFragment =
            childFragmentManager.findFragmentById(R.id.map_fragment) as SupportMapFragment

        mapFragment.getMapAsync(
            OnMapReadyCallback { gMap ->
                try {
                    googleMap = gMap

                    googleMap.apply {
                        clear()
                        addMarker(
                            MarkerOptions().position(coordinates!!).title(address)
                                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                        )
                        moveCamera(CameraUpdateFactory.newLatLngZoom(coordinates, 12f))

                        uiSettings.isZoomControlsEnabled = true // плюс и минус
                        isMyLocationEnabled = true
                        isTrafficEnabled = true
//                        isBuildingsEnabled = true
                        uiSettings.isScrollGesturesEnabled = true // движения карты
                        uiSettings.isZoomGesturesEnabled = true // разрешить зумирование
                        uiSettings.isMapToolbarEnabled = true // отображение названий мест на карте
                    }

                } catch (e: Exception) {
                    Log.e("@@@", "Ошибка при настройке карты: ${e.message}", e)
                }
            }
        )
    }

    private fun enableLocation() {
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                0
            )
            return
        }
    }

    private fun generateTextViewTechnologiesAndSetValues(
        flexboxLayout: FlexboxLayout,
        vacancyAdditionEntity: VacancyAdditionEntity,
        sectionType: String
    ) {
        // FlexboxLayout - библиотека позволила переносить элементы на новую строчку
        // при отсутствии места в строке
        val technologiesList = getTechnologiesString(
            vacancyAdditionEntity,
            sectionType
        ).second

        // Очищаем контейнер перед добавлением новых TextView
        flexboxLayout.removeAllViews()

        // Создаем и добавляем TextView для каждого значения в списке
        for (technology in technologiesList) {
            val textView = TextView(context).apply {
                val layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                )
                layoutParams.leftMargin = resources.getDimensionPixelSize(R.dimen.default_margin)
                layoutParams.rightMargin = resources.getDimensionPixelSize(R.dimen.default_margin)
                layoutParams.bottomMargin = resources.getDimensionPixelSize(R.dimen.default_margin)
                setLayoutParams(layoutParams)

                setPadding(
                    resources.getDimensionPixelSize(R.dimen.default_text_padding),
                    resources.getDimensionPixelSize(R.dimen.default_text_padding),
                    resources.getDimensionPixelSize(R.dimen.default_text_padding),
                    resources.getDimensionPixelSize(R.dimen.default_text_padding)
                )

                gravity = Gravity.START
                setBackgroundResource(R.drawable.my_radius_light_blue_200)
                text = technology
                setTextSize(
                    TypedValue.COMPLEX_UNIT_PX,
                    resources.getDimensionPixelSize(R.dimen.default_text).toFloat()
                )
            }
            flexboxLayout.addView(textView)
        }
    }

    /** Преобразуем строки -Технологии*/
    private fun getTechnologiesString(
        vacancyAdditionEntity: VacancyAdditionEntity,
        titleSectionType: String
    ): Pair<List<String>, List<String>> {

        val title = vacancyAdditionEntity.offerReducer?.offer?.sections?.mapNotNull { sections ->
            if (sections.sectionType == titleSectionType) {
                sections.title
            } else {
                null
            }
        }?.toFormattedString() ?: ""

        val titleExpectedAndOptional =
            vacancyAdditionEntity.offerReducer?.offer?.sections?.flatMap { sections ->
                if (sections.sectionType == "technologies") {
                    sections.subSections?.mapNotNull { subSections ->
                        if (subSections.sectionType == titleSectionType) {
                            subSections.title ?: ""
                        } else {
                            null
                        }
                    }
                } else {
                    emptyList()
                } ?: emptyList()
            }?.toFormattedString() ?: ""

        val textTechnologiesList =
            vacancyAdditionEntity.offerReducer?.offer?.sections?.flatMap { sections ->
                if (sections.sectionType == "technologies") {
                    sections.subSections?.flatMap { subSections ->
                        if (subSections.sectionType == titleSectionType) {
                            subSections.model?.customItems?.mapNotNull { customItems ->
                                "${customItems.name}"
                            } ?: emptyList()
                        } else {
                            emptyList()
                        }
                    }
                } else {
                    emptyList()
                } ?: emptyList()
            } ?: emptyList()
        return Pair(listOf(title, titleExpectedAndOptional), textTechnologiesList)
    }

    /** Преобразуем строку Требования - Опционально*/
    private fun getRequirementsOptionalString(
        vacancyAdditionEntity: VacancyAdditionEntity
    ): Pair<String, String> {
        var title = vacancyAdditionEntity.offerReducer?.offer?.sections?.mapNotNull {
            if (it.sectionType == "requirements") {
                it.subSections?.mapNotNull { requirementsOptional ->
                    if (requirementsOptional.sectionType == "requirements-optional") {
                        requirementsOptional.title
                    } else {
                        null
                    }
                }
            } else {
                null
            }
        }?.toFormattedString() ?: ""

        val textRequirements = vacancyAdditionEntity.offerReducer?.offer?.sections?.flatMap {
            if (it.sectionType == "requirements") {
                it.subSections?.flatMap { subSections ->
                    if (subSections.sectionType == "requirements-optional") {
                        title = subSections.title ?: ""
                        subSections.model?.bullets?.mapNotNull { bullet ->
                            "@ $bullet"
                        } ?: emptyList()
                    } else {
                        emptyList()
                    }
                }
            } else {
                emptyList()
            } ?: emptyList()
        }?.joinToString("\n\n") ?: ""
        return Pair(title, textRequirements)
    }

    /** Преобразуем строку Требования */
    private fun getRequirementsString(
        vacancyAdditionEntity: VacancyAdditionEntity
    ): Pair<String, String> {
        val title = vacancyAdditionEntity.offerReducer?.offer?.sections?.mapNotNull {
            if (it.sectionType == "requirements") {
                it.title
            } else {
                null
            }
        }?.toFormattedString() ?: ""

        val textRequirements =
            vacancyAdditionEntity.offerReducer?.offer?.sections?.flatMap { sections ->
                if (sections.sectionType == "requirements") {
                    if (sections.subSections != null) {
                        sections.subSections?.flatMap { requirements ->
                            if (requirements.sectionType == "requirements-expected") {
                                requirements.model?.bullets?.mapNotNull { bullet ->
                                    "@ $bullet"
                                }
                            } else {
                                null
                            } ?: emptyList()
                        }
                    } else {
                        sections.model?.paragraphs
                    }
                } else {
                    null
                } ?: emptyList()
            }?.joinToString("\n\n")?.toList()?.joinToString("") ?: ""
        return Pair(title, textRequirements)
    }

    /** Преобразуем строки:
     * 1. Обязанности
     * 2. О проекте */
    private fun getResponsibilitiesString(
        vacancyAdditionEntity: VacancyAdditionEntity,
        sectionType: String,
        lineDesignation: String = ""
    ): Pair<String, String> {
        val title = vacancyAdditionEntity.offerReducer?.offer?.sections?.mapNotNull {
            if (it.sectionType == sectionType) {
                it.title
            } else {
                null
            }
        }?.toFormattedString() ?: ""

        val textResponsibilities =
            vacancyAdditionEntity.offerReducer?.offer?.sections?.flatMap { sections ->
                if (sections.sectionType == sectionType) {
                    /** Из-за то что от источника приходят разные названия полей,
                     * заведена переменная bullets*/
                    val bullets = sections.model?.bullets ?: sections.model?.paragraphs
                    bullets?.mapNotNull { bullet ->
                        "$lineDesignation $bullet"
                    }
                } else {
                    null
                } ?: emptyList()
            }?.joinToString("\n\n")?.toList()?.joinToString("") ?: ""

        return Pair(title, textResponsibilities)
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

    /** Разбиение текста по символам. С "точки" начинаем красную строку */
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