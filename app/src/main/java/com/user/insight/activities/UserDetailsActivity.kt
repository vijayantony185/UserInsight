package com.user.insight.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.user.insight.R
import com.user.insight.data.UserDetailsViewModel
import com.user.insight.data.UserDetailsViewModelFactory
import com.user.insight.data.Users
import com.user.insight.databinding.ActivityUserDetailsBinding

class UserDetailsActivity : AppCompatActivity() {
    private lateinit var binding : ActivityUserDetailsBinding
    private lateinit var userDetailsViewModel : UserDetailsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_user_details)
        userDetailsViewModel = ViewModelProvider(this, UserDetailsViewModelFactory())[UserDetailsViewModel::class.java]
        val userId = intent.getStringExtra("UserId")
        initToolbar()
        binding.lifecycleOwner = this
        binding.viewModel = userDetailsViewModel
        userId?.let {
            userDetailsViewModel.getSingleUser(it)
        }
        userDetailsViewModel.singleUserDetailsResponse.observe(this) {
            binding.progressBar.visibility = View.GONE
            configUI(it)
        }
    }

    private fun initToolbar() {
        val username = intent.getStringExtra("UserName")
        username?.let {
            binding.toolbar.title = it
        }
        binding.toolbar.setNavigationIcon(R.drawable.arrow_back_24)
        binding.toolbar.setTitleTextColor(ContextCompat.getColor(this,R.color.white))
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }

    private fun configUI(user : Users){
        Glide.with(this).load(user.image).into(binding.ivUserImage)
    }
}