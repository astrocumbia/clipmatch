package cc.hubble.clipmatch.data.repository

import cc.hubble.clipmatch.core.hobbies
import cc.hubble.clipmatch.core.songs
import cc.hubble.clipmatch.core.toModel
import cc.hubble.clipmatch.data.model.Song
import cc.hubble.clipmatch.data.model.User
import cc.hubble.network.service.RandomUserService
import javax.inject.Inject

class RandomUsersRepositoryImp @Inject constructor(
    private val randomUserService: RandomUserService
) : RandomUsersRepository {

    private var list: List<User> = mutableListOf()

    override suspend fun getUsers(): List<User> {
        val results = randomUserService.userList().results

        list = results.map { it.toModel() }

        return list
    }

    override suspend fun getUser(index: Int): User {
        return list[index]
    }

    override suspend fun getUserHobbies(index: Int): List<String> {
        return hobbies.shuffled().take(3).toList()
    }

    override suspend fun getUserFavoriteSong(index: Int): Song {
        return songs[index % songs.size]
    }
}