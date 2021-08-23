package cc.hubble.clipmatch.features.userList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import cc.hubble.clipmatch.data.model.User
import cc.hubble.clipmatch.databinding.FragmentUserListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserListFragment : Fragment() {

    private val viewModel: UserListViewModel by viewModels()

    private lateinit var _binding: FragmentUserListBinding
    private val binding: FragmentUserListBinding
        get() = _binding

    private val adapter: UserListAdapter
    get() = binding.userList.adapter as UserListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        configureRecyclerView()
        configureViewModel()
    }

    private fun configureViewModel() {
        viewModel.getUsersList()
        viewModel.state.observe(viewLifecycleOwner, ::handleStates)
        viewModel.event.observe(viewLifecycleOwner, ::handleEvent)
    }

    private fun handleStates(viewState: UserListViewModel.ViewState) {
        when (viewState) {
            is UserListViewModel.ViewState.UserList -> handleUserList(viewState)
        }
    }

    private fun handleEvent(event: UserListViewModel.Event?) {
        when (event) {
            is UserListViewModel.Event.ShowUserDetails -> showUserDetail(event.index)
        }
    }

    private fun handleUserList(viewState: UserListViewModel.ViewState.UserList) {
        adapter.submitList(viewState.users)
    }

    private fun showUserDetail(index: Int) {
        viewModel.clearUserSelected()

        val direction = UserListFragmentDirections
            .actionUserListFragmentToUserDetailFragment(index)

        findNavController().navigate(direction)
    }

    private fun configureRecyclerView() {
        binding.userList.apply {
            adapter = UserListAdapter { index ->
                viewModel.onUserSelected(index)
            }

            setHasFixedSize(true)
        }
    }

}