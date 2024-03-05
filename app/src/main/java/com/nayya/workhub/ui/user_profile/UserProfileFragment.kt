package com.nayya.workhub.ui.user_profile

import android.content.Context
import com.nayya.workhub.databinding.FragmentUserProfileBinding
import com.nayya.workhub.ui.root.ViewBindingFragment

class UserProfileFragment : ViewBindingFragment<FragmentUserProfileBinding>(
    FragmentUserProfileBinding::inflate
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
        fun newInstance() = UserProfileFragment()
    }
}