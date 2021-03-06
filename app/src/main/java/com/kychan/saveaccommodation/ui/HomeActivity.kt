package com.kychan.saveaccommodation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.kychan.saveaccommodation.base.BaseActivity
import com.kychan.saveaccommodation.R
import com.kychan.saveaccommodation.databinding.ActivityHomeBinding
import com.kychan.saveaccommodation.ui.accommodation.AccommodationFragment
import com.kychan.saveaccommodation.ui.bookmark.BookmarkFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseActivity<ActivityHomeBinding>({ ActivityHomeBinding.inflate(it) }
) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportFragmentManager.beginTransaction()
            .replace(
                R.id.home_frg_container,
                AccommodationFragment.newInstance(),
                resources.getString(R.string.accommodation_list)
            )
            .commit()

        setView()
    }

    private fun setView() {
        with(binding) {
            toolbar.title = getString(R.string.accommodation_list)
            val studyTabList = resources.getStringArray(R.array.home_tab)
            studyTabList.forEach { title ->
                bottomTab.addTab(bottomTab.newTab().setText(title))
            }

            bottomTab.run {
                addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                    override fun onTabSelected(tab: TabLayout.Tab?) {
                        tab ?: return

                        toolbar.title = tab.text
                        val selectTabFragment = supportFragmentManager.findFragmentByTag("${tab.text}")
                        if (selectTabFragment == null) {
                            supportFragmentManager.beginTransaction()
                                .add(R.id.home_frg_container, getFragment(tab.position), "${tab.text}")
                                .commit()
                        } else {
                            supportFragmentManager.beginTransaction()
                                .show(selectTabFragment)
                                .commit()
                        }
                    }

                    override fun onTabUnselected(tab: TabLayout.Tab?) {
                        tab ?: return
                        val hideFragment = supportFragmentManager.findFragmentByTag("${tab.text}")
                        if (hideFragment != null) {
                            supportFragmentManager.beginTransaction()
                                .hide(hideFragment)
                                .commit()
                        }
                    }

                    override fun onTabReselected(tab: TabLayout.Tab?) {
                    }
                })
            }
        }
    }

    private fun getFragment(position: Int): Fragment {
        return when (position) {
            0 -> AccommodationFragment.newInstance()
            1 -> BookmarkFragment.newInstance()
            else -> error("Invalid position")
        }
    }
}