package xlet.android.example.githubusers.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import xlet.android.example.githubusers.model.User
import xlet.android.example.githubusers.model.User.Companion.DIFF_UTIL

class PagingUserAdapter : PagingDataAdapter<User, UserViewHolder>(DIFF_UTIL) {
    private lateinit var layoutInflater: LayoutInflater

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        layoutInflater = LayoutInflater.from(recyclerView.context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        UserViewHolder(layoutInflater, parent)

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bindData(getItem(position)!!)
    }
}