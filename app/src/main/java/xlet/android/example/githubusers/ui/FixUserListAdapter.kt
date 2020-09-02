package xlet.android.example.githubusers.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import xlet.android.example.githubusers.model.User
import xlet.android.example.githubusers.model.User.Companion.DIFF_UTIL

class FixUserListAdapter : ListAdapter<User, UserViewHolder>(DIFF_UTIL) {
    private lateinit var layoutInflater: LayoutInflater

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        layoutInflater = LayoutInflater.from(recyclerView.context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        UserViewHolder(layoutInflater, parent)

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }
}

