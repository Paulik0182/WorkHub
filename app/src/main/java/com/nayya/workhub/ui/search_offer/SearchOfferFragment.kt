package com.nayya.workhub.ui.search_offer

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.nayya.workhub.databinding.FragmentSearchOfferBinding
import com.nayya.workhub.domain.entity.filter_category.filter_repo_interactor.ConditionSelectionVacancyInteractor
import com.nayya.workhub.ui.root.ViewBindingFragment

class SearchOfferFragment : ViewBindingFragment<FragmentSearchOfferBinding>(
    FragmentSearchOfferBinding::inflate
) {

    private val conditionSelectionVacancyInteractor: ConditionSelectionVacancyInteractor by lazy {
        app.conditionSelectionVacancyInteractor
    }

    private val viewModel: SearchOfferViewModel by lazy {
        ViewModelProvider(
            this,
            SearchOfferViewModel.Factory(
                conditionSelectionVacancyInteractor
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
    }

    @SuppressLint("SetTextI18n")
    private fun initView() {

        viewModel.distanceFromCityLiveData.observe(viewLifecycleOwner) { distance ->
            binding.distanceFromCityEditText.setText("+ ${distance.distanceFromCityInKm} km.")
        }
    }

    private fun getController(): Controller = activity as Controller

    interface Controller {
        fun openFilterCategoryVacancies()
        fun openDistanceFormCity()
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