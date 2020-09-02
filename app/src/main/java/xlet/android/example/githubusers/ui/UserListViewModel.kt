package xlet.android.example.githubusers.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import xlet.android.example.githubusers.repository.UserRepository

class UserListViewModel @ViewModelInject constructor(
    private val _userRepository: UserRepository
) : ViewModel() {
    val userList by lazy { _userRepository.getFirstUserList() }
}