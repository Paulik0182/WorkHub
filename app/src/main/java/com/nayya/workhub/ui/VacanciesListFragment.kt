package com.nayya.workhub.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nayya.workhub.databinding.FragmentWorkHubBinding
import com.nayya.workhub.domain.interactor.CollectionVacanciesInteractor
import com.nayya.workhub.ui.root.ViewBindingFragment

class VacanciesListFragment : ViewBindingFragment<FragmentWorkHubBinding>(
    FragmentWorkHubBinding::inflate
) {

    private val collectionVacanciesInteractor: CollectionVacanciesInteractor by lazy {
        app.collectionVacanciesInteractor
    }

    //    private val viewModel: WorkHubViewModel by viewModels()
    private val viewModel: VacanciesListViewModel by lazy {
        ViewModelProvider(
            this,
            VacanciesListViewModel.Factory(collectionVacanciesInteractor)
        )[VacanciesListViewModel::class.java]
    }

    private lateinit var adapter: VacanciesListAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()

        viewModel.vacanciesLiveData.observe(viewLifecycleOwner) {
            adapter.setData(it)
        }
    }

    private fun initView() {
        recyclerView = binding.workListRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = VacanciesListAdapter()
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
        fun newInstance() = VacanciesListFragment()
    }
}