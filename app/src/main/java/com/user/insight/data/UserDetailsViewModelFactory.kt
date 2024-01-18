package com.user.insight.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class UserDetailsViewModelFactory :  ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return UserDetailsViewModel(repo = UserDetailsRepo()) as T
    }
}