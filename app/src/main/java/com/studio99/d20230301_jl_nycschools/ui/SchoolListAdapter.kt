package com.studio99.d20230301_jl_nycschools.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.studio99.d20230301_jl_nycschools.BR
import com.studio99.d20230301_jl_nycschools.R
import com.studio99.d20230301_jl_nycschools.databinding.ViewListItemBinding
import com.studio99.d20230301_jl_nycschools.models.SchoolOverview

class SchoolListAdapter : ListAdapter<SchoolOverview, ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        SchoolOverviewViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.view_list_item,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        (holder as SchoolOverviewViewHolder).bind(getItem(position))
    }

    class SchoolOverviewViewHolder(itemBinding: ViewListItemBinding) : ViewHolder(itemBinding.root) {
        private val binding = itemBinding
        fun bind(item: SchoolOverview) =
            binding.setVariable(BR.model, item)
    }

    class DiffCallback : DiffUtil.ItemCallback<SchoolOverview>() {
        override fun areItemsTheSame(oldItem: SchoolOverview, newItem: SchoolOverview) =
            oldItem.dbn == newItem.dbn
        override fun areContentsTheSame(oldItem: SchoolOverview, newItem: SchoolOverview) =
            oldItem == newItem
    }
}