package com.nayya.workhub.ui.root

import android.animation.ValueAnimator
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nayya.workhub.R
import com.nayya.workhub.databinding.ActivityRootBinding
import com.nayya.workhub.domain.entity.offer.OfferJob
import com.nayya.workhub.domain.entity.offer.OfferListItem
import com.nayya.workhub.ui.VacanciesListFragment
import com.nayya.workhub.ui.documents_user.DocumentsUserFragment
import com.nayya.workhub.ui.job_details.JobDetailsFragment
import com.nayya.workhub.ui.job_details.city_for_work.CityForWorkFragment
import com.nayya.workhub.ui.notification_offer.NotificationOfferFragment
import com.nayya.workhub.ui.save_offer.SaveOfferFragment
import com.nayya.workhub.ui.search_offer.SearchOfferFragment
import com.nayya.workhub.ui.search_offer.filter.FilterCategoryVacanciesFragment
import com.nayya.workhub.ui.settings.SettingsFragment
import com.nayya.workhub.ui.user_profile.UserProfileFragment
import java.util.Stack

private const val TAG_ROOT_CONTAINER_FRAGMENT = "TAG_ROOT_CONTAINER_FRAGMENT"
private const val TAG_CITY_FOR_WORK_FRAGMENT = "TAG_CITY_FOR_WORK_FRAGMENT"
private const val TAG_JOB_DETAILS_FRAGMENT = "TAG_JOB_DETAILS_FRAGMENT"
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
    JobDetailsFragment.Controller,
    CityForWorkFragment.Controller {

    private var isSlaveBnbVisible = false

    private val fragmentStack = Stack<Fragment>()
    private val handler: Handler = Handler(Looper.getMainLooper())

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

    private fun swapFragmentBackStack(fragment: Fragment, teg: String) {
        supportFragmentManager
            .beginTransaction()
            .add(binding.container.id, fragment, teg)
            .addToBackStack(teg)
            .commit()
    }

    override fun openFilterCategoryVacancies() {
        val fragment = FilterCategoryVacanciesFragment.newInstance()
        swapFragmentBackStack(fragment, TAG_ROOT_CONTAINER_FRAGMENT)
    }

    override fun openDetailsVacancyJob(offerListItem: OfferListItem) {
        val manyCities: Boolean = (offerListItem.offers?.size ?: 0) > 1

        val offers = offerListItem.offers?.firstOrNull()

        if (!manyCities) {
            val fragment = JobDetailsFragment.newInstance(offers)
            swapFragmentBackStack(fragment, TAG_JOB_DETAILS_FRAGMENT)
        } else {
            val fragment = CityForWorkFragment.newInstance(
                offerListItem.groupId
            )
            swapFragmentBackStack(fragment, TAG_CITY_FOR_WORK_FRAGMENT)
        }
    }

    override fun openDetailsVacancyJobSpecifiedCity(offerListItem: OfferJob) {

//        supportFragmentManager.popBackStack() /** Есть ли еще варианты убирать фрагмент из стэка
//         перед открытием следующего фрагмента. Этот вариант сильно тормозит */

        val fragment = JobDetailsFragment.newInstance(offerListItem)
        swapFragmentBackStack(fragment, TAG_JOB_DETAILS_FRAGMENT)

        handler.post {
            deleteFragmentByTagFromBackStack(TAG_CITY_FOR_WORK_FRAGMENT)
        }
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

    private fun deleteFragmentByTagFromBackStack(tegFragment: String) {
        val fragmentManager = supportFragmentManager
        val backStackCount = fragmentManager.backStackEntryCount

// Получение списка всех фрагментов в стеке обратного вызова
        val fragmentList = mutableListOf<Fragment>()
        for (i in 0 until backStackCount) {
            val backStackEntry = fragmentManager.getBackStackEntryAt(i)
            val fragmentTag = backStackEntry.name
            val fragment = fragmentManager.findFragmentByTag(fragmentTag)
            fragment?.let {
                fragmentList.add(it)
            }
        }

// Удаление конкретного фрагмента из стека обратного вызова
        val fragmentToRemove = fragmentList.firstOrNull {
            it.tag == tegFragment
        }
        fragmentToRemove?.let {
            val transaction = fragmentManager.beginTransaction()
            transaction.remove(it)
            transaction.commit()
        }
    }

    private fun addToBackStack(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        val currentFragment = supportFragmentManager.findFragmentById(binding.container.id)
        if (currentFragment != null) {
            transaction.hide(currentFragment)
        }
        transaction.add(binding.container.id, fragment, TAG_ROOT_CONTAINER_FRAGMENT)
        transaction.commit()
        fragmentStack.push(fragment)
    }

    private fun popBackStack() {
        if (!fragmentStack.empty()) {
            val removedFragment = fragmentStack.pop()
            val transaction = supportFragmentManager.beginTransaction()
            transaction.remove(removedFragment)
            if (!fragmentStack.empty()) {
                val lastFragment = fragmentStack.peek()
                transaction.show(lastFragment)
            }
            transaction.commit()
        }
    }

    override fun onBackPressed() {
        if (fragmentStack.size > 1) {
            popBackStack()
        } else {
            super.onBackPressed()
        }
    }
}