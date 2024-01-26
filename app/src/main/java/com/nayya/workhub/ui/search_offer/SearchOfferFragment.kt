package com.nayya.workhub.ui.search_offer

import android.content.Context
import com.nayya.workhub.databinding.FragmentSearchOfferBinding
import com.nayya.workhub.ui.root.ViewBindingFragment

class SearchOfferFragment : ViewBindingFragment<FragmentSearchOfferBinding>(
    FragmentSearchOfferBinding::inflate
) {

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
        fun newInstance() = SearchOfferFragment()
    }
}