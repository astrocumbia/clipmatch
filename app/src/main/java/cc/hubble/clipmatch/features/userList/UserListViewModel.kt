package cc.hubble.clipmatch.features.userList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cc.hubble.clipmatch.data.model.User
import cc.hubble.clipmatch.data.repository.RandomUsersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val repo: RandomUsersRepository
): ViewModel() {

    private val _state = MutableLiveData<ViewState>()
    val state: LiveData<ViewState> = _state

    private val _event = MutableLiveData<Event?>()
    val event: LiveData<Event?> = _event

    fun getUsersList() {
        viewModelScope.launch {
            val users  = repo.getUsers()
            _state.value = ViewState.UserList(users)
        }
    }

    fun onUserSelected(index: Int) {
        _event.value = Event.ShowUserDetails(index)
    }

    fun clearUserSelected() {
        _event.value = null
    }

    // states
    sealed class ViewState {
        data class UserList(val users: List<User>): ViewState()
    }

    // Events
    sealed class Event {
        data class ShowUserDetails(val index:Int): Event()
    }
}