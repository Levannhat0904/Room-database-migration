package net.braniumacademy.lesson12_11.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import net.braniumacademy.lesson12_11.data.model.User
import net.braniumacademy.lesson12_11.ui.MainActivity
import net.braniumacademy.lesson12_11.R
import net.braniumacademy.lesson12_11.databinding.UserItemBinding

class UserAdapter(
    private var users: List<User>?,
    private val listener: MainActivity.OptionMenuClickListener
) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {
    init {
        this.users = users ?: ArrayList()
    }

    class ViewHolder(
        v: View,
        binding: UserItemBinding,
        private val listener: MainActivity.OptionMenuClickListener
    ) : RecyclerView.ViewHolder(v) {
        private val binding: UserItemBinding
        private lateinit var user: User

        init {
            this.binding = binding
            this.binding.btnOption.setOnClickListener { onOptionMenuClick() }
        }

        fun bindData(user: User) {
            this.user = user
            binding.textEmail.text = user.email
            binding.textFullname.text = user.fullName
            binding.textDisplayName.text = user.displayName
        }

        private fun onOptionMenuClick() {
            val popup = PopupMenu(binding.root.context, binding.btnOption)
            popup.inflate(R.menu.option_menu)
            popup.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.menu_item_delete -> {
                        listener.delete(user)
                        true
                    }

                    R.id.menu_item_update -> {
                        listener.update(user)
                        true
                    }

                    R.id.menu_item_detail -> {
                        listener.viewDetail(user)
                        true
                    }

                    else -> false
                }
            }
            popup.show()
        }
    }

    fun updateData(data: List<User>) {
        this.users = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: UserItemBinding = UserItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root, binding, listener)
    }

    override fun getItemCount(): Int {
        return users?.size ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(users?.get(position)!!)
    }
}