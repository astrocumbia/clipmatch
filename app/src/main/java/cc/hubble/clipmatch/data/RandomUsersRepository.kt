package cc.hubble.clipmatch.data

import cc.hubble.clipmatch.data.model.User
import cc.hubble.network.RandomUserApiClient

interface RandomUsersRepository {
    suspend fun getUsers(): List<User>
}

class RandomUsersRepositoryImp : RandomUsersRepository {
    // todo inject client
    override suspend fun getUsers(): List<User> {
        val response = RandomUserApiClient.randomUserService.userList()
        return response.results.map { User(name = "${it.name.title} ${it.name.first} ${it.name.last}") }
    }
}