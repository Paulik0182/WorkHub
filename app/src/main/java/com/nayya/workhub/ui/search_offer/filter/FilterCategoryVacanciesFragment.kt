package com.nayya.workhub.ui.search_offer.filter

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nayya.workhub.databinding.FragmentFilterCategoryVacanciesBinding
import com.nayya.workhub.domain.interactor.CategorySelectionInteractor
import com.nayya.workhub.ui.root.ViewBindingFragment

class FilterCategoryVacanciesFragment : ViewBindingFragment<FragmentFilterCategoryVacanciesBinding>(
    FragmentFilterCategoryVacanciesBinding::inflate
) {

    private val categorySelectionInteractor: CategorySelectionInteractor by lazy {
        app.categorySelectionInteractor
    }

    //    private val savedStateHandle: SavedStateHandle by viewModels()
//    private val savedStateHandle: SavedStateHandle by lazy {
//        findNavController().currentBackStackEntry?.savedStateHandle ?:
//        throw IllegalStateException("ViewLifecycleOwner doesn't have a SavedStateHandle")
//    }

    private val savedStateHandle = SavedStateHandle()

    private val viewModel: FilterCategoryVacanciesViewModel by lazy {
        ViewModelProvider(
            this,
            FilterCategoryVacanciesViewModel.Factory(
                categorySelectionInteractor,
                savedStateHandle
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

        restoreStare()
    }

    private fun initView() {
        recyclerView = binding.categoryVacanciesListRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = FilterCategoryVacanciesAdapter { id, selection ->
            categorySelectionInteractor.setSelection(id, selection)
        }
        recyclerView.adapter = adapter
    }

    private fun saveState() {
//        viewModel.saveSelectedCategories(adapter.getSelectedCategories())
    }

    private fun restoreStare() {
        val savedCategories = viewModel.getSelectedCategories()
        savedCategories?.let {
//            adapter.setData(savedCategories)
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

    override fun onDestroyView() {
        super.onDestroyView()
        saveState()
    }

    companion object {

        @JvmStatic
        fun newInstance() = FilterCategoryVacanciesFragment()
    }
}