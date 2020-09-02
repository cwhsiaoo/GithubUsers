package xlet.android.example.githubusers.repository

import xlet.android.example.githubusers.repository.api.GithubApi
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val _githubApi: GithubApi
) {
    fun getFirstUserList() = _githubApi.getFirstUserList()
}