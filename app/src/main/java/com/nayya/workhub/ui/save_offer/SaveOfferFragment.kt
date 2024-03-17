package com.nayya.workhub.ui.save_offer

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nayya.workhub.databinding.FragmentSaveOfferBinding
import com.nayya.workhub.domain.interactor.CollectionVacanciesInteractor
import com.nayya.workhub.ui.root.ViewBindingFragment

class SaveOfferFragment : ViewBindingFragment<FragmentSaveOfferBinding>(
    FragmentSaveOfferBinding::inflate
) {

    private val collectionVacanciesInteractor: CollectionVacanciesInteractor by lazy {
        app.collectionVacanciesInteractor
    }

    private val viewModel: SaveOfferViewModel by lazy {
        ViewModelProvider(
            this,
            SaveOfferViewModel.Factory(collectionVacanciesInteractor)
        )[SaveOfferViewModel::class.java]
    }

    private lateinit var adapter: SaveOfferAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()

        viewModel.favoriteVacanciesLiveData.observe(viewLifecycleOwner) {
            adapter.setData(it)
        }
    }

    private fun initView() {
        recyclerView = binding.workListRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = SaveOfferAdapter(
            data = emptyList(),
            onDetailsJobListener = {
                viewModel.onVacancyJobClick(it)
            },
            context = requireContext(),
            viewModel = viewModel
        )
        recyclerView.adapter = adapter
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
        fun newInstance() = SaveOfferFragment()
    }
}