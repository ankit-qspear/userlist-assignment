package com.example.assignmentuserlist.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.assignmentuserlist.models.User
import com.example.assignmentuserlist.retrofit.GetUserService

class UserViewModel @ViewModelInject constructor(
    private val getUserService : GetUserService
    ) : ViewModel() {

    val userList = liveData<List<User>> {
        val result = getUserService.getUserList()
        emit(result.userList)
    }


}