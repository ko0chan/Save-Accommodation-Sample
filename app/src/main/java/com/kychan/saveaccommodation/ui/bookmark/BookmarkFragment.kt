package com.kychan.saveaccommodation.ui.bookmark

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.kychan.saveaccommodation.base.BaseFragment
import com.kychan.saveaccommodation.R
import com.kychan.saveaccommodation.databinding.FragmentBookmarkBinding
import com.kychan.saveaccommodation.ui.AccommodationDetailActivity
import com.kychan.saveaccommodation.ui.SortType
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
            rvBookmark.adapter = bookmarkAdapter

            ArrayAdapter.createFromResource(
                requireContext(),
                R.array.sort_array,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                sortSpinner.adapter = adapter
            }
            sortSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    bookmarkAdapter.submitList(null)
                    when (position) {
                        0 -> {
                            bookmarkViewModel.setSortType(SortType.RECENT)
                        }
                        1 -> {
                            bookmarkViewModel.setSortType(SortType.RATING)
                        }
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }

            }
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