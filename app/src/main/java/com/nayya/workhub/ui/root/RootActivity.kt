package com.nayya.workhub.ui.root

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.nayya.workhub.databinding.ActivityRootBinding
import com.nayya.workhub.ui.VacanciesListFragment

private const val TAG_ROOT_CONTAINER_FRAGMENT = "TAG_ROOT_CONTAINER_FRAGMENT"

class RootActivity : ViewBindingActivity<ActivityRootBinding>(
    ActivityRootBinding::inflate
),
    RootFragment.Controller,
    VacanciesListFragment.Controller {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            val fragment = RootFragment.newInstance()
            swapFragment(fragment)
        }
    }

    private fun swapFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .add(binding.container.id, fragment, TAG_ROOT_CONTAINER_FRAGMENT)
            .commit()
    }

    private fun swapFragmentBackStack(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .add(binding.container.id, fragment, TAG_ROOT_CONTAINER_FRAGMENT)
            .addToBackStack(null)
            .commit()
    }

    override fun openWorkHub() {
        val fragment = VacanciesListFragment.newInstance()
        swapFragmentBackStack(fragment)
    }
}