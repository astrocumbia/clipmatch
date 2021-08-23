package cc.hubble.clipmatch.data.repository

import cc.hubble.clipmatch.data.model.Song
import cc.hubble.clipmatch.data.model.User

interface RandomUsersRepository {
    suspend fun getUsers(): List<User>
    suspend fun getUser(index: Int): User
    suspend fun getUserHobbies(index: Int): List<String>
    suspend fun getUserFavoriteSong(index: Int): Song
}