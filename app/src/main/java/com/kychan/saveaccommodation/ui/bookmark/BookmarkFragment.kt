package com.kychan.saveaccommodation.ui.bookmark

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.kychan.saveaccommodation.base.BaseFragment
import com.kychan.saveaccommodation.ui.HomeActivity
import com.kychan.saveaccommodation.R
import com.kychan.saveaccommodation.databinding.FragmentBookmarkBinding
import com.kychan.saveaccommodation.ui.AccommodationDetailActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookmarkFragment : BaseFragment<FragmentBookmarkBinding>() {

    private val bookmarkViewModel by viewModels<BookmarkViewModel>()

    private val bookmarkAdapter by lazy {
        BookmarkAdapter({
            startActivity(AccommodationDetailActivity.getIntent(requireContext(), it.toAccommodationItem()))
        }, {
            bookmarkViewModel.deleteAccommodation(it.id)
        })
    }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentBookmarkBinding {
        return FragmentBookmarkBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setView()
        setViewModel()
    }

    private fun setView() {
        with(binding) {
            (activity as HomeActivity).supportActionBar?.title = getString(R.string.bookmark)
            rvBookmark.adapter = bookmarkAdapter
        }
    }

    private fun setViewModel() {
        with(bookmarkViewModel) {
            bookmarkList.observe(viewLifecycleOwner, {
                bookmarkAdapter.submitList(it)
                binding.emptyView.isVisible = it.isNullOrEmpty()
            })
        }
    }

    companion object {
        fun newInstance() =
            BookmarkFragment()
    }
}