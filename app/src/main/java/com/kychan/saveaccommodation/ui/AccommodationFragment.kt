package com.kychan.saveaccommodation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.iron.espresso.base.BaseFragment
import com.kychan.saveaccommodation.HomeActivity
import com.kychan.saveaccommodation.R
import com.kychan.saveaccommodation.databinding.FragmentAccommodationBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AccommodationFragment : BaseFragment<FragmentAccommodationBinding>() {

    private val accommodationViewModel by viewModels<AccommodationViewModel>()

    private val accommodationAdapter by lazy {
        AccommodationAdapter({
            //item click
        }, {
            if (it.isBookmark) {
                accommodationViewModel.deleteAccommodation(it.id)
            } else {
                accommodationViewModel.insertAccommodation(it)
            }
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
        setViewModel()
    }

    private fun setView() {
        with(binding) {
            (activity as HomeActivity).supportActionBar?.title = getString(R.string.accommodation_list)
            rvAccommodation.adapter = accommodationAdapter
        }
    }

    private fun setViewModel() {
        with(accommodationViewModel) {
            accommodationList.observe(viewLifecycleOwner, {
                accommodationAdapter.submitData(lifecycle, it)
            })
        }
    }

    companion object {
        fun newInstance() =
            AccommodationFragment()
    }
}