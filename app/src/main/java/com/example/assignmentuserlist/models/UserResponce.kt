package com.example.assignmentuserlist.models


import com.google.gson.annotations.SerializedName

data class UserResponce(
    @SerializedName("userList")
    val userList: List<User>
)