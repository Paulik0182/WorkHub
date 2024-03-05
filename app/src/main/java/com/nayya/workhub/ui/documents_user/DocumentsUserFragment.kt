package com.nayya.workhub.ui.documents_user

import android.content.Context
import com.nayya.workhub.databinding.FragmentDocumentsUserBinding
import com.nayya.workhub.ui.root.ViewBindingFragment

class DocumentsUserFragment : ViewBindingFragment<FragmentDocumentsUserBinding>(
    FragmentDocumentsUserBinding::inflate
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
        fun newInstance() = DocumentsUserFragment()
    }
}