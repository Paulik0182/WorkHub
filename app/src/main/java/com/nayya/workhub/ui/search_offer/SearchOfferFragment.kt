package com.nayya.workhub.ui.search_offer

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Typeface
import android.os.Bundle
import android.text.TextUtils
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.google.android.flexbox.FlexboxLayout
import com.nayya.workhub.R
import com.nayya.workhub.databinding.FragmentSearchOfferBinding
import com.nayya.workhub.domain.entity.filter_category.LocationEntity
import com.nayya.workhub.domain.entity.filter_category.filter_repo_interactor.DistanceConditionSelectionVacancyInteractor
import com.nayya.workhub.domain.entity.filter_category.filter_repo_interactor.LocationSelectionInteractor
import com.nayya.workhub.ui.root.ViewBindingFragment

class SearchOfferFragment : ViewBindingFragment<FragmentSearchOfferBinding>(
    FragmentSearchOfferBinding::inflate
) {

    private val distanceConditionSelectionVacancyInteractor: DistanceConditionSelectionVacancyInteractor by lazy {
        app.distanceConditionSelectionVacancyInteractor
    }

    private val locationSelectionInteractor: LocationSelectionInteractor by lazy {
        app.locationSelectionInteractor
    }

    private val viewModel: SearchOfferViewModel by lazy {
        ViewModelProvider(
            this,
            SearchOfferViewModel.Factory(
                distanceConditionSelectionVacancyInteractor,
                locationSelectionInteractor
            )
        )[SearchOfferViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initButton()

        initView()
    }

    private fun initButton() {
        binding.filterCategoryButton.setOnClickListener {
            getController().openFilterCategoryVacancies()
        }

        binding.distanceFromCityEditText.setOnClickListener {
            getController().openDistanceFormCity()
        }

        binding.hintText.setOnClickListener {
            getController().openLocation()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun initView() {

        viewModel.distanceFromCityLiveData.observe(viewLifecycleOwner) { distance ->
            binding.distanceFromCityEditText.setText("+ ${distance.distanceFromCityInKm} km.")
        }

        viewModel.locationLiveData.observe(viewLifecycleOwner) {
            generateTextViewTechnologiesAndSetValues(
                binding.locationFilterContainer as FlexboxLayout,
                it
            )
        }
    }

    private fun generateTextViewTechnologiesAndSetValues(
        flexboxLayout: FlexboxLayout,
        data: List<Pair<LocationEntity, Boolean>>
    ) {
        // FlexboxLayout - библиотека позволила переносить элементы на новую строчку
        // при отсутствии места в строке

        val viewMap =
            mutableMapOf<LocationEntity, TextView>() // Карта для хранения пар (LocationEntity, TextView)

        val locationList = data.mapNotNull {
            it.first
        }

        val allow = data.mapNotNull {
            it.second
        }

        clearFlexboxLayout(
            flexboxLayout,
            data
        ) // Очищаем контейнер перед добавлением новых TextView

        // Создаем и добавляем TextView для каждого значения в списке
        for ((index, location) in locationList.withIndex()) {
            if (allow[index]) {
                val textView = TextView(context).apply {
                    val layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                    )
                    layoutParams.leftMargin =
                        resources.getDimensionPixelSize(R.dimen.default_margin)
                    layoutParams.rightMargin =
                        resources.getDimensionPixelSize(R.dimen.default_margin)
                    layoutParams.bottomMargin =
                        resources.getDimensionPixelSize(R.dimen.default_margin)
                    setLayoutParams(layoutParams)

                    setPadding(
                        resources.getDimensionPixelSize(R.dimen.default_text_padding),
                        resources.getDimensionPixelSize(R.dimen.default_text_padding),
                        resources.getDimensionPixelSize(R.dimen.default_text_padding),
                        resources.getDimensionPixelSize(R.dimen.default_text_padding)
                    )

                    gravity = Gravity.START or Gravity.CENTER_VERTICAL
                    setBackgroundResource(R.drawable.my_radius_light_blue_200)
                    text = location.locality
                    setTextSize(
                        TypedValue.COMPLEX_UNIT_PX,
                        resources.getDimensionPixelSize(R.dimen.default_text).toFloat()
                    )
                    compoundDrawablePadding =
                        resources.getDimensionPixelSize(R.dimen.default_margin)
                    setCompoundDrawablesWithIntrinsicBounds(
                        0,
                        0,
                        R.drawable.icon_close_24,
                        0
                    ) // Установка drawableEnd

                    setOnClickListener {
                        removeLocationFromList(
                            location,
                            data.toMutableList(),
                            flexboxLayout,
                            viewMap
                        )
                    }
                }
                flexboxLayout.addView(textView)
                viewMap[location] = textView  // Сохраняем пару (LocationEntity, TextView) в карту
            }
        }
    }

    private fun clearFlexboxLayout(
        flexboxLayout: FlexboxLayout,
        data: List<Pair<LocationEntity, Boolean>>
    ) {
        // Проверяем наличие элементов с флагом true в списке
        val hasTrueValues = data.any { it.second }

        // Очищаем все дочерние элементы FlexboxLayout
        flexboxLayout.removeAllViews()

        // Если нет элементов с true, генерируем один дефолтный элемент
        if (!hasTrueValues) {
            val hintTextView = TextView(context).apply {
                id = R.id.hint_text
                val layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                )
                layoutParams.marginEnd =
                    resources.getDimensionPixelSize(R.dimen.default_text_padding)

                setPadding(
                    0,
                    resources.getDimensionPixelSize(R.dimen.default_headlines_text_padding),
                    resources.getDimensionPixelSize(R.dimen.super_max_text_padding),
                    resources.getDimensionPixelSize(R.dimen.default_headlines_text_padding)
                )

                setTextColor(ContextCompat.getColor(context, R.color.gray_white))
                hint = resources.getString(R.string.search_city)
                setTypeface(typeface, Typeface.BOLD)
                gravity = Gravity.START or Gravity.CENTER_VERTICAL
                setTextSize(
                    TypedValue.COMPLEX_UNIT_PX,
                    resources.getDimensionPixelSize(R.dimen.default_subtitle_text).toFloat()
                )

                ellipsize = TextUtils.TruncateAt.END
                maxLines = 1

                setOnClickListener {
                    getController().openLocation()
                }
            }
            flexboxLayout.addView(hintTextView)
        }
    }

    private fun removeLocationFromList(
        location: LocationEntity,
        data: MutableList<Pair<LocationEntity, Boolean>>,
        flexboxLayout: FlexboxLayout,
        viewsMap: MutableMap<LocationEntity, TextView>
    ) {
        val iterator = data.iterator()
        while (iterator.hasNext()) {
            val pair = iterator.next()
            if (pair.first == location) {
                iterator.remove()
                val textView = viewsMap[location]
                textView?.let {
                    flexboxLayout.removeView(textView)
                    viewsMap.remove(location)
                }
                break
            }
        }
    }

    private fun getController(): Controller = activity as Controller

    interface Controller {
        fun openFilterCategoryVacancies()
        fun openDistanceFormCity()

        fun openLocation()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        getController()
    }

    companion object {
        @JvmStatic
        fun newInstance() = SearchOfferFragment()
    }
}