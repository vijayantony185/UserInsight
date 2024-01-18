package com.user.insight.network

import com.user.insight.data.UserDetails
import com.user.insight.data.Users
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {
    @GET("users?")
    fun getUsers(@Query("limit") limit : Int, @Query("skip") skip : Int) : Call<UserDetails>

    @GET("users/{userId}")
    fun getUserById(@Path("userId") userId: String) : Call<Users>
}