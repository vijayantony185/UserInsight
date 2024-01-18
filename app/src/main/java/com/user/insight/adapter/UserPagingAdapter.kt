package com.user.insight.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.user.insight.data.Users
import com.user.insight.databinding.AdapterUserBinding

class UserPagingAdapter(val context: Context,val itemCLicked : ItemCLicked) :
    PagingDataAdapter<Users, UserPagingAdapter.ViewHolder>(USER_COMPARATOR) {
    inner class ViewHolder(private val binding : AdapterUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: Users) {
            binding.tvUserName.text = user.firstName
            binding.tvMobile.text = user.phone
            binding.tvEmail.text = user.email
            Glide.with(context).load(user.image).into(binding.ivUser)
            binding.clUser.setOnClickListener {
                itemCLicked.clicked(user)
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = getItem(position)
        user?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = AdapterUserBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    companion object {
        private val USER_COMPARATOR = object : DiffUtil.ItemCallback<Users>() {
            override fun areItemsTheSame(oldItem: Users, newItem: Users): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Users, newItem: Users): Boolean {
                return oldItem == newItem
            }
        }
    }

}

interface ItemCLicked{
    fun clicked(user : Users)
}

/*class UserPagingAdapter : PagingDataAdapter<Users, RecyclerView.ViewHolder>(COMPARATOR){

    inner class UserPagingViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        holder.itemView.findViewById<TextView>(R.id.tvUserName).text = item?.firstName
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_user, parent, false)
        return UserPagingViewHolder(view)
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<Users>(){
            override fun areItemsTheSame(oldItem: Users, newItem: Users): Boolean {
               return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Users, newItem: Users): Boolean {
                return oldItem == newItem
            }

        }
    }
}*/

/*class UserPagingViewHolder(private val binding: AdapterUserBinding) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun create(view: ViewGroup) : UserPagingViewHolder {
            val inflater = LayoutInflater.from(view.context)
            val binding = AdapterUserBinding.inflate(inflater, view, false)
            return UserPagingViewHolder(binding)
        }
    }

    fun bind(item : Users?) {
        binding.tvUserName.text = item?.firstName
    }
}*/
