package xlet.android.example.githubusers.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_user_list.*
import xlet.android.example.githubusers.R

@AndroidEntryPoint
class UserListFragment : Fragment(R.layout.fragment_user_list) {
    private val viewModel by viewModels<UserListViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        FixUserListAdapter().let { adapter ->
            viewModel.userList
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ list ->
                    adapter.submitList(list)
                }) { throwable ->
                    Log.e(tag, "api fail", throwable)
                }
            recycler_view.adapter = adapter
        }
    }
}