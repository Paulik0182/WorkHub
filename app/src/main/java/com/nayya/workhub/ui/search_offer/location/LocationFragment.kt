package com.nayya.workhub.ui.search_offer.location

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nayya.workhub.databinding.FragmentLocationBinding
import com.nayya.workhub.domain.entity.filter_category.filter_repo_interactor.LocationSelectionInteractor
import com.nayya.workhub.ui.root.ViewBindingFragment

class LocationFragment : ViewBindingFragment<FragmentLocationBinding>(
    FragmentLocationBinding::inflate
) {

    var maxTextLength = 0
    var currentSearchText: String? = null

    private val locationSelectionInteractor: LocationSelectionInteractor by lazy {
        app.locationSelectionInteractor
    }

    private val viewModel: LocationViewModel by lazy {
        ViewModelProvider(
            this,
            LocationViewModel.Factory(
                locationSelectionInteractor
            )
        )[LocationViewModel::class.java]
    }

    private lateinit var adapter: LocationAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()

        initData()
    }

    private fun initData() {
        viewModel.locationLiveData.observe(viewLifecycleOwner) {
            adapter.setData(it)
        }
    }

    private fun initView() {
        recyclerView = binding.locationListRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)

        adapter = LocationAdapter(
            context = requireActivity(),
            listener = { id, selection ->
                locationSelectionInteractor.setLocationSelection(id, selection)
                viewModel.refresh()
            }
        )

        val closeButton: ImageView =
            binding.searchView.findViewById(androidx.appcompat.R.id.search_close_btn)

        closeButton.setOnClickListener {
            // Очистка текста в SearchView
            binding.searchView.setQuery("", false)
            // Сокрытие клавиатуры
            binding.searchView.clearFocus()
            // Возвращение списка к первоначальному состоянию
            initData()
        }

        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Выполняется перед изменением текста
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Выполняется при изменении текста, перед применением изменений
                val text = s?.toString()
                val currentLength = text?.length ?: 0
                if (currentLength < maxTextLength) {
                    // Код, который нужно выполнить при удалении символа из строки поиска
                    initData()
                    maxTextLength = currentLength
                } else {
                    maxTextLength = currentLength
                }
                currentSearchText = text
                searchForLocality()
            }

            override fun afterTextChanged(s: Editable?) {
                // Выполняется после изменения текста
            }
        }

        binding.searchView.findViewById<EditText>(androidx.appcompat.R.id.search_src_text)
            .addTextChangedListener(textWatcher)
//
//        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                return false
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//                currentSearchText = newText
//                searchForLocality()
//                return true
//            }
//        })

        recyclerView.adapter = adapter
    }

    private fun searchForLocality() {
        val text = currentSearchText
        // Ваш код для поиска в списке на основе текста
        if (text != null) {
            adapter.filterList1(text)
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

    companion object {

        @JvmStatic
        fun newInstance() =
            LocationFragment().apply {
                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
                }
            }
    }
}