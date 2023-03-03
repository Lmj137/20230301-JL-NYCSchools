package com.studio99.d20230301_jl_nycschools.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.studio99.d20230301_jl_nycschools.R
import com.studio99.d20230301_jl_nycschools.presenters.SchoolListViewModel
import kotlinx.coroutines.launch

/**
 * Need to add loading indicator.
 */
class SchoolListFragment : Fragment(R.layout.fragment_school_list) {
    companion object { const val TAG = "school_list_fragment" }

    private lateinit var schoolList: RecyclerView
    private val listAdapter = SchoolListAdapter()
    private val viewModel: SchoolListViewModel by viewModels()
    private val dialogFragment = SchoolDetailsFragment()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        schoolList = view.findViewById<RecyclerView>(R.id.fragment_school_list).apply {
            adapter = listAdapter
            layoutManager = LinearLayoutManager(context)
        }

        viewLifecycleOwner.apply {
            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.CREATED) {

                    viewModel.overviewFlow.collect { list ->
                        list.forEach {
                            it.clickAction = { openDetails(it.dbn) }
                        }
                        listAdapter.submitList(list)
                    }
                }
            }
        }
    }

    private fun openDetails(dbn: String) {
        viewModel.currentSchool = dbn
        dialogFragment.show(childFragmentManager, SchoolDetailsFragment.TAG)
    }
}