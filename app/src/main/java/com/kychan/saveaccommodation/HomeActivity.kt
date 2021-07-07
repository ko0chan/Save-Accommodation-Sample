package com.kychan.saveaccommodation

import android.os.Bundle
import com.iron.espresso.base.BaseActivity
import com.kychan.saveaccommodation.databinding.ActivityHomeBinding
import com.kychan.saveaccommodation.ui.AccommodationListFragment

class HomeActivity : BaseActivity<ActivityHomeBinding>({ ActivityHomeBinding.inflate(it) }
) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportFragmentManager.beginTransaction()
            .replace(
                R.id.home_frg_container,
                AccommodationListFragment.newInstance(),
                resources.getString(R.string.accommodation_list)
            )
            .commit()

        setView()
    }

    private fun setView() {
        with(binding) {

        }
    }
}