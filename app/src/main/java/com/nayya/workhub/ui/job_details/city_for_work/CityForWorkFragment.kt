package com.nayya.workhub.ui.job_details.city_for_work

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nayya.workhub.databinding.FragmentCityForWorkBinding
import com.nayya.workhub.domain.entity.offer.OfferJob
import com.nayya.workhub.domain.entity.offer.repo.PracujPlOffersJobRepo
import com.nayya.workhub.ui.root.ViewBindingFragment

private const val OFFER_ID = "OFFER_ID"

class CityForWorkFragment : ViewBindingFragment<FragmentCityForWorkBinding>(
    FragmentCityForWorkBinding::inflate
) {

    private val pracujPlOffersJobRepo: PracujPlOffersJobRepo by lazy {
        app.pracujPlOffersJobRepo
    }

    private val viewModel: CityForWorkViewModel by lazy {
        ViewModelProvider(
            this,
            CityForWorkViewModel.Factory(
                pracujPlOffersJobRepo = pracujPlOffersJobRepo,
                groupId = arguments?.getString(OFFER_ID) ?: "" // todo возможна ошибка - обработать
            )
        )[CityForWorkViewModel::class.java]
    }

    private lateinit var adapter: CityForWorkAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()

        viewModel.cityLiveData.observe(viewLifecycleOwner) {
            adapter.setData(it)
        }

        viewModel.selectedCityLiveData.observe(viewLifecycleOwner) {
            getController().openDetailsVacancyJobSpecifiedCity(it)
        }
    }

    private fun initView() {
        recyclerView = binding.cityForWorkListRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = CityForWorkAdapter(
            data = emptyList(),
            onDetailsJobListener = {
                viewModel.onCityClick(it)
            }
        )
        recyclerView.adapter = adapter
    }

    private fun getController(): Controller = activity as Controller

    interface Controller {
        fun openDetailsVacancyJobSpecifiedCity(
            offerListItem: OfferJob,
        )
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        getController()
    }

    companion object {

        @JvmStatic
        fun newInstance(
            groupId: String?
        ) = CityForWorkFragment().apply {
            arguments = Bundle().apply {
                putString(OFFER_ID, groupId)
            }
        }
    }
}