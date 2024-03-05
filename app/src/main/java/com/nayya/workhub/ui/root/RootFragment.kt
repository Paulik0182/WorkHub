package com.nayya.workhub.ui.root

import android.content.Context
import android.os.Bundle
import android.view.View
import com.nayya.workhub.databinding.FragmentRootBinding

class RootFragment : ViewBindingFragment<FragmentRootBinding>(
    FragmentRootBinding::inflate
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initButton()
    }

    private fun initButton() {

        binding.workHubButton.setOnClickListener {
//            getController().openWorkHub()
        }
    }

    private fun getController(): Controller = activity as Controller

    interface Controller {
//        fun openWorkHub()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        getController()
    }

    companion object {
        @JvmStatic
        fun newInstance() = RootFragment()
    }
}