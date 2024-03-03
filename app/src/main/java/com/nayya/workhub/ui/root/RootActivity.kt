package com.nayya.workhub.ui.root

import android.animation.ValueAnimator
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.nayya.workhub.R
import com.nayya.workhub.databinding.ActivityRootBinding
import com.nayya.workhub.domain.entity.offer.OfferListItem
import com.nayya.workhub.ui.VacanciesListFragment
import com.nayya.workhub.ui.documents_user.DocumentsUserFragment
import com.nayya.workhub.ui.job_details.JobDetailsFragment
import com.nayya.workhub.ui.notification_offer.NotificationOfferFragment
import com.nayya.workhub.ui.save_offer.SaveOfferFragment
import com.nayya.workhub.ui.search_offer.SearchOfferFragment
import com.nayya.workhub.ui.search_offer.filter.FilterCategoryVacanciesFragment
import com.nayya.workhub.ui.settings.SettingsFragment
import com.nayya.workhub.ui.user_profile.UserProfileFragment

private const val TAG_ROOT_CONTAINER_FRAGMENT = "TAG_ROOT_CONTAINER_FRAGMENT"
private const val ANIMATION_TIME_BOTTOM_NAV_BAR = 500L
private const val CLOSING_DELAY_TIME_BOTTOM_NAV_BAR = 500L

class RootActivity : ViewBindingActivity<ActivityRootBinding>(
    ActivityRootBinding::inflate
),
    RootFragment.Controller,
    VacanciesListFragment.Controller,
    SearchOfferFragment.Controller,
    SaveOfferFragment.Controller,
    DocumentsUserFragment.Controller,
    UserProfileFragment.Controller,
    NotificationOfferFragment.Controller,
    SettingsFragment.Controller,
    FilterCategoryVacanciesFragment.Controller,
    JobDetailsFragment.Controller {

    private var isSlaveBnbVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        onSlaveDeviceBottomNavBar()
        onRootBottomNavBar()

        if (savedInstanceState == null) {
            binding.rootBottomNavBar.selectedItemId = R.id.my_offer_item
        }
    }

    private fun onRootBottomNavBar() {
        binding.rootBottomNavBar.setOnItemSelectedListener {
            title = it.title
            when (it.itemId) {
                R.id.my_offer_item -> {
                    swapFragment(VacanciesListFragment.newInstance())
                    hideSlaveDeviceBottomNavigationView()
                }

                R.id.search_item -> {
                    swapFragment(SearchOfferFragment.newInstance())
                    hideSlaveDeviceBottomNavigationView()
                }

                R.id.saved_item -> {
                    swapFragment(SaveOfferFragment.newInstance())
                    hideSlaveDeviceBottomNavigationView()
                }

                R.id.more_item -> {
                    movementSlaveDeviceBottomNavBar()
                }
            }
            return@setOnItemSelectedListener true
        }
    }

    private fun onSlaveDeviceBottomNavBar() {
        binding.slaveDeviceBottomNavBar.setOnItemSelectedListener {
            title = it.title
            when (it.itemId) {
                R.id.profile_item -> {
                    swapFragment(UserProfileFragment.newInstance())
                    hideSlaveDeviceBottomNavigationView()
                }

                R.id.documents_item -> {
                    swapFragment(DocumentsUserFragment.newInstance())
                    hideSlaveDeviceBottomNavigationView()
                }

                R.id.notification_item -> {
                    swapFragment(NotificationOfferFragment.newInstance())
                    hideSlaveDeviceBottomNavigationView()
                }

                R.id.settings_item -> {
                    swapFragment(SettingsFragment.newInstance())
                    hideSlaveDeviceBottomNavigationView()
                }
            }
            return@setOnItemSelectedListener true
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

    override fun openFilterCategoryVacancies() {
        val fragment = FilterCategoryVacanciesFragment.newInstance()
        swapFragmentBackStack(fragment)
    }

    override fun openDetailsVacancyJob(offerListItem: OfferListItem) {

        val value: Boolean = (offerListItem.offers?.size ?: 0) > 1

        if (!value) {
            val fragment = JobDetailsFragment.newInstance(offerListItem)
            swapFragmentBackStack(fragment)
        } else {
            Toast.makeText(
                this,
                "Необходимо обработать множественные ссылки на вакансии. То-есть, имеются множество ссылок в зависимости от городов вакансий",
                Toast.LENGTH_SHORT
            ).show()
        }
        /** !!! необходимо создать промежуточный фрагмент с выбором городов !!!*/
    }

    private fun movementSlaveDeviceBottomNavBar() {
        if (!isSlaveBnbVisible) {
            showSlaveDeviceBottomNavigationView()
        } else {
            hideSlaveDeviceBottomNavigationView()
        }
    }

    private fun hideSlaveDeviceBottomNavigationView() {
        val menu: Menu = binding.rootBottomNavBar.menu
        val menuItem = menu.findItem(R.id.more_item)

        val bottomBarHeight = binding.slaveDeviceBottomNavBar.height.toFloat()
        val linearLayout = binding.slaveDeviceBottomNavBar
        val shiftView = binding.shiftView

        val marginAnimator = ValueAnimator.ofInt(0, -bottomBarHeight.toInt())

        marginAnimator.addUpdateListener { animator ->
            val value = animator.animatedValue as Int
            val layoutParams = linearLayout.layoutParams as ViewGroup.MarginLayoutParams
            layoutParams.setMargins(
                layoutParams.leftMargin,
                layoutParams.topMargin,
                layoutParams.rightMargin,
                value
            )
            linearLayout.requestLayout()

            shiftView.layoutParams = shiftView.layoutParams.apply {
                height = bottomBarHeight.toInt() * 2 + value
            }
        }
        marginAnimator.duration = ANIMATION_TIME_BOTTOM_NAV_BAR
        marginAnimator.start()

        isSlaveBnbVisible = false

        // Изменение иконки
        menuItem.setIcon(R.drawable.ellipsis_24)

        // Изменение текста
        menuItem.setTitle(R.string.more)

        Handler(Looper.getMainLooper()).postDelayed({
            binding.slaveDeviceBottomNavBar.visibility = View.GONE
        }, CLOSING_DELAY_TIME_BOTTOM_NAV_BAR)
    }

    private fun showSlaveDeviceBottomNavigationView() {
        val menu: Menu = binding.rootBottomNavBar.menu
        val menuItem = menu.findItem(R.id.more_item)

        val bottomBarHeight = binding.slaveDeviceBottomNavBar.height.toFloat()
        val linearLayout = binding.slaveDeviceBottomNavBar
        val shiftView = binding.shiftView

        val marginAnimator = ValueAnimator.ofInt(0, bottomBarHeight.toInt())

        marginAnimator.addUpdateListener { animator ->
            val value = animator.animatedValue as Int
            val layoutParams = linearLayout.layoutParams as ViewGroup.MarginLayoutParams
            layoutParams.setMargins(
                layoutParams.leftMargin,
                layoutParams.topMargin,
                layoutParams.rightMargin,
                value - bottomBarHeight.toInt()
            )
            linearLayout.requestLayout()

            shiftView.layoutParams = shiftView.layoutParams.apply {
                height = bottomBarHeight.toInt() + value
            }
        }
        binding.slaveDeviceBottomNavBar.visibility = View.VISIBLE
        marginAnimator.duration = ANIMATION_TIME_BOTTOM_NAV_BAR
        marginAnimator.start()

        isSlaveBnbVisible = true

        // Изменение иконки
        menuItem.setIcon(R.drawable.close_nav_24)

        // Изменение текста
        menuItem.setTitle(R.string.close_two_nav)
    }
}