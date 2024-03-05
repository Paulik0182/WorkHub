package com.nayya.workhub.ui.search_offer.distance_in_km

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nayya.workhub.databinding.FragmentDistanceFromCityBinding
import com.nayya.workhub.domain.entity.filter_category.filter_repo.ConditionSelectionVacancyRepo
import com.nayya.workhub.ui.root.ViewBindingFragment

class DistanceFromCityFragment : ViewBindingFragment<FragmentDistanceFromCityBinding>(
    FragmentDistanceFromCityBinding::inflate
) {

    private lateinit var adapter: DistanceFromCityAdapter
    private lateinit var recyclerView: RecyclerView

    private val conditionSelectionVacancyRepo: ConditionSelectionVacancyRepo by lazy {
        app.conditionSelectionVacancyRepo
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()


    }

    private fun initView() {
        recyclerView = binding.distanceListRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = DistanceFromCityAdapter(
            data = emptyList(),
            onClickDistanceListener = { id, selection ->
                ""

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