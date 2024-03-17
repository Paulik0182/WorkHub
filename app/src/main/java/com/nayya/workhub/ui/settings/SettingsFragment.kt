package com.nayya.workhub.ui.settings

import android.content.Context
import com.nayya.workhub.databinding.FragmentSettingsBinding
import com.nayya.workhub.ui.root.ViewBindingFragment


class SettingsFragment : ViewBindingFragment<FragmentSettingsBinding>(
    FragmentSettingsBinding::inflate
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
        fun newInstance() = SettingsFragment()
    }
}