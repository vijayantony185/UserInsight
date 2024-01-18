package com.user.insight.data

import com.user.insight.network.ApiInterface
import com.user.insight.network.RetrofitClient
import com.user.insight.paging.UsersPagingDataSource

class UserDetailsRepo {
    private val retrofit = RetrofitClient.getRetrofitInstance().create(ApiInterface::class.java)

    fun getSingleUser(userId: String) = retrofit.getUserById(userId)

    fun getPagingUserData(): UsersPagingDataSource {
        return UsersPagingDataSource(retrofit)
    }
}