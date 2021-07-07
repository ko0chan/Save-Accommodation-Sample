package com.kychan.saveaccommodation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.iron.espresso.base.BaseFragment
import com.kychan.saveaccommodation.databinding.FragmentAccommodationListBinding

class AccommodationListFragment : BaseFragment<FragmentAccommodationListBinding>() {

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentAccommodationListBinding {
        return FragmentAccommodationListBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    companion object {
        fun newInstance() =
            AccommodationListFragment()
    }
}