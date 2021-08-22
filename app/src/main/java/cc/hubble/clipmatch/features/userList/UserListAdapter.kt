package cc.hubble.clipmatch.features.userList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import cc.hubble.clipmatch.data.model.User
import cc.hubble.clipmatch.databinding.ItemUserBinding
import cc.hubble.clipmatch.features.userList.UserListAdapter.UserViewHolder
import com.bumptech.glide.Glide

class UserListAdapter() : ListAdapter<User, UserViewHolder>(UserDiff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val viewHolder = UserViewHolder(ItemUserBinding.inflate(layoutInflater, parent, false))

        return viewHolder
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    // ViewHolder
    class UserViewHolder(
        private val binding: ItemUserBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User) {
            binding.itemName.text = user.name
            binding.itemNationality.text = user.nationality
            binding.itemImage.clipToOutline = true

            Glide.with(binding.root).load(user.thumbnail).into(binding.itemImage)
        }
    }

    object  UserDiff : DiffUtil.ItemCallback<User> () {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }

    }
}