package com.example.assignmentuserlist.retrofit

import com.example.assignmentuserlist.models.UserResponce
import retrofit2.http.GET

interface GetUserService {

    @GET("/v2/5c4650753100004c0005f340")
    suspend fun getUserList() : UserResponce

}