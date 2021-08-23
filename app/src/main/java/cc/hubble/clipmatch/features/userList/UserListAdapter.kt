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

class UserListAdapter(
    private val onItemClicked: (Int) -> Unit,
) : ListAdapter<User, UserViewHolder>(UserDiff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemUserBinding = ItemUserBinding.inflate(layoutInflater, parent, false)
        val viewHolder = UserViewHolder(itemUserBinding).apply {
            itemView.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    onItemClicked(adapterPosition)
                }
            }
        }

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
            binding.itemName.text = user.fullName
            binding.itemNationality.text = user.nationality
            binding.itemImage.clipToOutline = true

            Glide.with(binding.root).load(user.thumbnail).into(binding.itemImage)
        }
    }

    object  UserDiff : DiffUtil.ItemCallback<User> () {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.fullName == newItem.fullName
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }

    }
}