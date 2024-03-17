package com.nayya.workhub.ui.search_offer.distance_in_km

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nayya.workhub.databinding.FragmentDistanceFromCityBinding
import com.nayya.workhub.domain.entity.filter_category.filter_repo_interactor.ConditionSelectionVacancyInteractor
import com.nayya.workhub.ui.root.ViewBindingFragment

class DistanceFromCityFragment : ViewBindingFragment<FragmentDistanceFromCityBinding>(
    FragmentDistanceFromCityBinding::inflate
) {

    private val conditionSelectionVacancyInteractor: ConditionSelectionVacancyInteractor by lazy {
        app.conditionSelectionVacancyInteractor
    }

    private val viewModel: DistanceFromCityViewModel by lazy {
        ViewModelProvider(
            this,
            DistanceFromCityViewModel.Factory(
                conditionSelectionVacancyInteractor
            )
        )[DistanceFromCityViewModel::class.java]
    }

    private lateinit var adapter: DistanceFromCityAdapterTest
    private lateinit var recyclerView: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()

        viewModel.distanceFromCityLiveData.observe(viewLifecycleOwner) {
            adapter.setData(it)
        }
        viewModel.selectedDistanceFromCityLiveData.observe(viewLifecycleOwner) {
            adapter.setSelection(it)
        }
    }

    private fun initView() {
        recyclerView = binding.distanceListRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = DistanceFromCityAdapterTest(
            data = emptyList(),
            onClickDistanceListener = { id ->
                conditionSelectionVacancyInteractor.setSelectionDistance(id)
            }
        )
        recyclerView.adapter = adapter
    }

    private fun getController(): Controller = activity as Controller

    interface Controller {
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        getController()
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            DistanceFromCityFragment().apply {
                arguments = Bundle().apply {
//                    putString()
//                    putString()
                }
            }
    }
}