package com.studio99.d20230301_jl_nycschools.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.studio99.d20230301_jl_nycschools.R
import com.studio99.d20230301_jl_nycschools.databinding.FragmentSchoolDetailsBinding
import com.studio99.d20230301_jl_nycschools.models.SchoolDetails
import com.studio99.d20230301_jl_nycschools.presenters.SchoolListViewModel
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.onEmpty
import kotlinx.coroutines.launch

class SchoolDetailsFragment : DialogFragment(R.layout.fragment_school_details) {
    companion object { const val TAG = "details_dialog" }

    private var _binding: FragmentSchoolDetailsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SchoolListViewModel by viewModels({ requireParentFragment() })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSchoolDetailsBinding.bind(
            super.onCreateView(inflater, container, savedInstanceState)!!
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.apply {
            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.CREATED) {

                    viewModel.apply {
                        getSchoolDetails(currentSchool)
                            .collect { details ->
                                binding.bindInFragment(details)
                            }
                    }
                }
            }
        }
    }

    private fun FragmentSchoolDetailsBinding.bindInFragment(source: SchoolDetails) {
        schoolName.text = source.schoolName
        satCriticalReadingAvg.text = source.satReadingAvg
        satMathAvg.text = source.satMathAvg
        satWritingAvg.text = source.satWritingAvg
    }
}