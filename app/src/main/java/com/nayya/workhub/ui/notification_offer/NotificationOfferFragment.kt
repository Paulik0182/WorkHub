package com.nayya.workhub.ui.notification_offer

import android.content.Context
import com.nayya.workhub.databinding.FragmentNotificationOfferBinding
import com.nayya.workhub.ui.root.ViewBindingFragment

class NotificationOfferFragment : ViewBindingFragment<FragmentNotificationOfferBinding>(
    FragmentNotificationOfferBinding::inflate
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
        fun newInstance() = NotificationOfferFragment()
    }
}