package com.nayya.workhub.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nayya.workhub.databinding.FragmentWorkHubBinding
import com.nayya.workhub.domain.entity.offer.OfferListItem
import com.nayya.workhub.domain.entity.offer.repo.PracujPlOffersJobRepo
import com.nayya.workhub.ui.root.ViewBindingFragment
import com.nayya.workhub.utils.image.GlideImageLoader

class VacanciesListFragment : ViewBindingFragment<FragmentWorkHubBinding>(
    FragmentWorkHubBinding::inflate
) {

    private val pracujPlOffersJobRepo: PracujPlOffersJobRepo by lazy {
        app.pracujPlOffersJobRepo
    }

    private val viewModel: VacanciesListViewModel by lazy {
        ViewModelProvider(
            this,
            VacanciesListViewModel.Factory(
                pracujPlOffersJobRepo = pracujPlOffersJobRepo,
                context = requireContext()
            )
        )[VacanciesListViewModel::class.java]
    }

    private lateinit var adapter: VacanciesListAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()

        dataView()
    }

    private fun initView() {
        recyclerView = binding.workListRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = VacanciesListAdapter(
            data = emptyList(),
            onDetailsJobListener = {
                viewModel.onVacancyJobClick(it)
            },
            context = requireContext(),
            imageLoader = GlideImageLoader(),
            viewModel = viewModel
        )
        recyclerView.adapter = adapter

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val visibleItemCount = recyclerView.layoutManager?.childCount ?: 0
                val totalItemCount = recyclerView.layoutManager?.itemCount ?: 0
                val firstVisibleItemPosition =
                    (recyclerView.layoutManager as?
                            LinearLayoutManager)?.findFirstVisibleItemPosition()

                val threshold = 15 // элементов до конца прокрутки

                if (totalItemCount - visibleItemCount <= (firstVisibleItemPosition
                        ?: 0) + threshold && newState == RecyclerView.SCROLL_STATE_IDLE
                ) {
                    viewModel.onScrollFinish()
                }
            }
        })
    }

    private fun dataView() {
        viewModel.vacanciesLiveData.observe(viewLifecycleOwner) {
            adapter.setData(it)
        }

        viewModel.selectedVacancyJobLiveData.observe(viewLifecycleOwner) {
            getController().openDetailsVacancyJob(it)
        }
    }

    private fun getController(): Controller = activity as Controller

    interface Controller {
        fun openDetailsVacancyJob(offerListItem: OfferListItem)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        getController()
    }

    companion object {
        @JvmStatic
        fun newInstance() = VacanciesListFragment()
    }
}