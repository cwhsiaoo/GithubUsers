package xlet.android.example.githubusers.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.ExperimentalPagingApi
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_user_list.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import xlet.android.example.githubusers.R

@AndroidEntryPoint
class UserListFragment : Fragment(R.layout.fragment_user_list) {
    private val viewModel by viewModels<UserListViewModel>()

    @ExperimentalPagingApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        PagingUserAdapter().let { pagingUserAdapter ->
            recycler_view.adapter = pagingUserAdapter
            lifecycleScope.launch {
                viewModel.pagingUsers.collectLatest {
                    pagingUserAdapter.submitData(it)
                }
            }
        }
    }
}