package xlet.android.example.githubusers.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import xlet.android.example.githubusers.R

@AndroidEntryPoint
class UserListFragment : Fragment(R.layout.fragment_user_list) {
    private val viewModel by viewModels<UserListViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}