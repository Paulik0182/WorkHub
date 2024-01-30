package com.nayya.workhub.ui.search_offer

import android.content.Context
import android.os.Bundle
import android.view.View
import com.nayya.workhub.databinding.FragmentSearchOfferBinding
import com.nayya.workhub.ui.root.ViewBindingFragment

class SearchOfferFragment : ViewBindingFragment<FragmentSearchOfferBinding>(
    FragmentSearchOfferBinding::inflate
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initButton()
    }

    private fun initButton() {
        binding.filterCategoryButton.setOnClickListener {
            getController().openFilterCategoryVacancies()
        }
    }

    private fun getController(): Controller = activity as Controller

    interface Controller {
        fun openFilterCategoryVacancies()
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