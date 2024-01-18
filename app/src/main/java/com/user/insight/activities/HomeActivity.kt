package com.user.insight.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.user.insight.R
import com.user.insight.adapter.ItemCLicked
import com.user.insight.adapter.UserPagingAdapter
import com.user.insight.data.UserDetailsViewModel
import com.user.insight.data.UserDetailsViewModelFactory
import com.user.insight.data.Users
import com.user.insight.databinding.ActivityHomeBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeActivity : AppCompatActivity() {

    private lateinit var userDetailsViewModel : UserDetailsViewModel
    private lateinit var binding : ActivityHomeBinding
    private lateinit var adapter : UserPagingAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        binding.toolbar.title = ContextCompat.getString(this, R.string.toolbarTitle)
        setSupportActionBar(binding.toolbar)

        userDetailsViewModel = ViewModelProvider(this, UserDetailsViewModelFactory())[UserDetailsViewModel::class.java]
        adapter = UserPagingAdapter(this, object : ItemCLicked{
            override fun clicked(user: Users) {
                val intent = Intent(this@HomeActivity, UserDetailsActivity::class.java)
                intent.putExtra("UserName","${user.firstName } ${user.lastName}")
                intent.putExtra("UserId",user.id.toString())
                startActivity(intent)
            }
        })
        binding.rcvUsers.layoutManager = LinearLayoutManager(this)
        binding.rcvUsers.adapter = adapter
        lifecycleScope.launch {
            userDetailsViewModel.getUsers().collectLatest { pagingData ->
                adapter.submitData(pagingData)
            }
        }
    }
}