package com.example.assignmentuserlist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.assignmentuserlist.retrofit.GetUserService
import javax.inject.Inject

class UserViewModelFactory @Inject constructor(
    private val getUserService : GetUserService
    ) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return UserViewModel(getUserService) as T
    }

}