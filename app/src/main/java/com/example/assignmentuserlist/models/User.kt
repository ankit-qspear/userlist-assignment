package com.example.assignmentuserlist.models


import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("dailyWage")
    var dailyWage: Int,
    @SerializedName("id")
    val id: String,
    @SerializedName("lastName")
    val lastName: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("picture")
    val picture: String,
    @SerializedName("any")
    var noOfDays : Int
)