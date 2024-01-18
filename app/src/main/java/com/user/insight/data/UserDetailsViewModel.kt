package com.user.insight.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response

class UserDetailsViewModel(val repo: UserDetailsRepo) : ViewModel() {
    var _singleUserDetailsResponse = MutableLiveData<Users>()
    val singleUserDetailsResponse : LiveData<Users> = _singleUserDetailsResponse

    fun getSingleUser(userId : String) {
        viewModelScope.launch {
            repo.getSingleUser(userId).enqueue(object : retrofit2.Callback<Users>{
                override fun onResponse(call: Call<Users>, response: Response<Users>) {
                    if (response.isSuccessful) {
                        _singleUserDetailsResponse.value = response.body()
                    }
                }

                override fun onFailure(call: Call<Users>, t: Throwable) {

                }
            })
        }
    }

    fun getUsers(): Flow<PagingData<Users>> {
        return Pager(
            config = PagingConfig(pageSize = 10, enablePlaceholders = false, initialLoadSize = 10),
            pagingSourceFactory = { repo.getPagingUserData() }
        ).flow.cachedIn(viewModelScope)
    }
}