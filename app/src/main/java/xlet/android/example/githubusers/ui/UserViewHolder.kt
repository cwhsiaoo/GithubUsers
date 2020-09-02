package xlet.android.example.githubusers.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import xlet.android.example.githubusers.databinding.ListUserBinding
import xlet.android.example.githubusers.model.User

class UserViewHolder private constructor(
    private val _binding: ListUserBinding
) : RecyclerView.ViewHolder(_binding.root) {

    constructor(
        inflater: LayoutInflater,
        parent: ViewGroup
    ) : this(
        ListUserBinding.inflate(inflater, parent, false)
    )

    fun bindData(data: User) {
        _binding.user = data
    }
}