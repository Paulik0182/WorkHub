package com.nayya.workhub.ui.root

import android.os.Bundle
import android.view.Menu
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.LinearInterpolator
import androidx.fragment.app.Fragment
import com.nayya.workhub.R
import com.nayya.workhub.databinding.ActivityRootBinding
import com.nayya.workhub.ui.VacanciesListFragment
import com.nayya.workhub.ui.documents_user.DocumentsUserFragment
import com.nayya.workhub.ui.notification_offer.NotificationOfferFragment
import com.nayya.workhub.ui.save_offer.SaveOfferFragment
import com.nayya.workhub.ui.search_offer.SearchOfferFragment
import com.nayya.workhub.ui.settings.SettingsFragment
import com.nayya.workhub.ui.user_profile.UserProfileFragment

private const val TAG_ROOT_CONTAINER_FRAGMENT = "TAG_ROOT_CONTAINER_FRAGMENT"

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
    SettingsFragment.Controller {

    private var positionSlaveDeviceBnb = false

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
                    swapFragment(RootFragment.newInstance())
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

    override fun openWorkHub() {
        val fragment = VacanciesListFragment.newInstance()
        swapFragmentBackStack(fragment)
    }

    private fun movementSlaveDeviceBottomNavBar() {
        if (!positionSlaveDeviceBnb) {
            showSlaveDeviceBottomNavigationView()
        } else {
            hideSlaveDeviceBottomNavigationView()

//            if (binding.slaveDeviceBottomNavBar.getVisibility() == View.GONE) {
//                positionSlaveDeviceBnb = true
//                binding.slaveDeviceBottomNavBar.setVisibility(View.VISIBLE)
//                binding.slaveDeviceBottomNavBar.startAnimation(animalSlide(R.anim.slave_device_nav_in))
//            } else {
//                positionSlaveDeviceBnb = false
//                binding.slaveDeviceBottomNavBar.setVisibility(View.GONE)
//                binding.slaveDeviceBottomNavBar.startAnimation(animalSlide(R.anim.slave_device_nav_out))
//            }
        }
    }

    private fun hideSlaveDeviceBottomNavigationView() {
        val menu: Menu = binding.rootBottomNavBar.getMenu()
        val menuItem = menu.findItem(R.id.more_item)

        val translationY = binding.slaveDeviceBottomNavBar.height.toFloat()

        binding.rootBottomNavBar.animate()
            .translationY(translationY)
            .setInterpolator(LinearInterpolator())
            .setDuration(300)
            .start()

        binding.slaveDeviceBottomNavBar.animate()
            .translationY(translationY)
            .setInterpolator(LinearInterpolator())
            .setDuration(300)
            .withEndAction {

            }
            .start()
        positionSlaveDeviceBnb = false

        // Изменение иконки
        menuItem.setIcon(R.drawable.ellipsis_24)

        // Изменение текста
        menuItem.setTitle(R.string.more)
    }

    private fun showSlaveDeviceBottomNavigationView() {
        val menu: Menu = binding.rootBottomNavBar.getMenu()
        val menuItem = menu.findItem(R.id.more_item)

        binding.slaveDeviceBottomNavBar.animate()
            .withStartAction {
                binding.slaveDeviceBottomNavBar.setVisibility(View.VISIBLE)
                binding.slaveDeviceBottomNavBar.startAnimation(animalSlide(R.anim.slave_device_nav_in))
            }
            .translationY(0f)
            .setInterpolator(LinearInterpolator())
            .setDuration(300)
            .start()

        binding.rootBottomNavBar.animate()
            .withStartAction {
                binding.rootBottomNavBar.startAnimation(animalSlide(R.anim.slave_device_nav_in))
            }
            .translationY(0f)
            .setInterpolator(LinearInterpolator())
            .setDuration(300)
            .start()

        positionSlaveDeviceBnb = true

        // Изменение иконки
        menuItem.setIcon(R.drawable.close_nav_24)

        // Изменение текста
        menuItem.setTitle(R.string.close_two_nav)
    }

    private fun animalSlide(slaveDeviceNavIn: Int): Animation {
        return AnimationUtils.loadAnimation(
            applicationContext,
            slaveDeviceNavIn
        )
    }
}