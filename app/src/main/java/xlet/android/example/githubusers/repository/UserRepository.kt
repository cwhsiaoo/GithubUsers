package xlet.android.example.githubusers.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import xlet.android.example.githubusers.repository.api.GithubApi
import xlet.android.example.githubusers.repository.db.AppDatabase
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val _db: AppDatabase,
    private val _githubApi: GithubApi
) {
    fun getFirstUserList() = _githubApi.getFirstUserList()

    @ExperimentalPagingApi
    fun pagingUsers() = Pager(
        config = PagingConfig(20),
        remoteMediator = UserRemoteMediator(_githubApi, _db)
    ) {
        _db.userDao().allUsers()
    }.flow
}