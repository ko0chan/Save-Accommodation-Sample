package com.kychan.saveaccommodation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.iron.espresso.base.BaseFragment
import com.kychan.saveaccommodation.HomeActivity
import com.kychan.saveaccommodation.R
import com.kychan.saveaccommodation.databinding.FragmentAccommodationBinding

class AccommodationFragment : BaseFragment<FragmentAccommodationBinding>() {

    private val searchMovieAdapter by lazy {
        AccommodationAdapter({
            //item click
        }, {
            //bookmark click
        })
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentAccommodationBinding {
        return FragmentAccommodationBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setView()
    }

    private fun setView() {
        with(binding) {
            (activity as HomeActivity).supportActionBar?.title = getString(R.string.accommodation_list)
            rvAccommodation.adapter = searchMovieAdapter
        }
    }

    companion object {
        fun newInstance() =
            AccommodationFragment()
    }
}