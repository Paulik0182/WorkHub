package com.nayya.workhub.ui.save_offer

import android.content.Context
import com.nayya.workhub.databinding.FragmentSaveOfferBinding
import com.nayya.workhub.ui.root.ViewBindingFragment

class SaveOfferFragment : ViewBindingFragment<FragmentSaveOfferBinding>(
    FragmentSaveOfferBinding::inflate
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
        fun newInstance() = SaveOfferFragment()
    }
}