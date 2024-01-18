package com.user.insight.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.user.insight.data.UserDetails
import com.user.insight.data.Users
import com.user.insight.network.ApiInterface
import retrofit2.Call
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class UsersPagingDataSource(private val apiInterface: ApiInterface) : PagingSource<Int, Users>() {
    override fun getRefreshKey(state: PagingState<Int, Users>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Users> {
        return try {
            val page = params.key ?: 0
            val skip = 10
            val userList = suspendCoroutine<List<Users>> { continuation ->
                apiInterface.getUsers(page, page * skip)
                    .enqueue(object : retrofit2.Callback<UserDetails> {
                        override fun onResponse(
                            call: Call<UserDetails>,
                            response: Response<UserDetails>
                        ) {
                            response.body()?.let {
                                continuation.resume(it.users)
                            } ?: run {
                                continuation.resumeWithException(NullPointerException("Response body is null"))
                            }
                        }

                        override fun onFailure(call: Call<UserDetails>, t: Throwable) {
                            continuation.resumeWithException(t)
                        }
                    })
            }

            LoadResult.Page(
                data = userList,
                prevKey = if (page == 0) null else page - 1,
                nextKey = if (userList.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}