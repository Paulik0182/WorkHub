package com.nayya.workhub.ui.search_offer.filter

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nayya.workhub.databinding.FragmentFilterCategoryVacanciesBinding
import com.nayya.workhub.domain.entity.filter_category.filter_repo_interactor.CategorySelectionInteractor
import com.nayya.workhub.ui.root.ViewBindingFragment

class FilterCategoryVacanciesFragment : ViewBindingFragment<FragmentFilterCategoryVacanciesBinding>(
    FragmentFilterCategoryVacanciesBinding::inflate
) {

    private val categorySelectionInteractor: CategorySelectionInteractor by lazy {
        app.categorySelectionInteractor
    }

    private val viewModel: FilterCategoryVacanciesViewModel by lazy {
        ViewModelProvider(
            this,
            FilterCategoryVacanciesViewModel.Factory(
                categorySelectionInteractor
            )
        )[FilterCategoryVacanciesViewModel::class.java]
    }

    private lateinit var adapter: FilterCategoryVacanciesAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()

        viewModel.categoryVacanciesLiveData.observe(viewLifecycleOwner) {
            adapter.setData(it)
        }
    }

    private fun initView() {
        recyclerView = binding.categoryVacanciesListRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)

        adapter = FilterCategoryVacanciesAdapter(
            context = requireActivity(),
            listener = { id, selection ->
                categorySelectionInteractor.setSelection(id, selection)
                viewModel.refresh()
            }
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
        fun newInstance() = FilterCategoryVacanciesFragment()
    }
}