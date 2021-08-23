package cc.hubble.clipmatch.features.userDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cc.hubble.clipmatch.data.model.Song
import cc.hubble.clipmatch.data.model.User
import cc.hubble.clipmatch.data.repository.RandomUsersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(
    private val repo: RandomUsersRepository
): ViewModel() {

    private val _state = MutableLiveData<ViewState>()
    val state: LiveData<ViewState> = _state

    fun getUserDetail(index: Int) {
        viewModelScope.launch {
            val user = repo.getUser(index)
            val song = repo.getUserFavoriteSong(index)
            val hobbies = repo.getUserHobbies(index)
            _state.value = ViewState.UserDetail(user, song, hobbies)
        }
    }

    // states
    sealed class ViewState {
        data class UserDetail(val user: User, val favoriteSong: Song, val hobbies: List<String>): ViewState()
    }
}