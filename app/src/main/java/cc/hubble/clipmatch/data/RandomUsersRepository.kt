package cc.hubble.clipmatch.data

import cc.hubble.clipmatch.data.model.User
import cc.hubble.network.service.RandomUserService
import javax.inject.Inject

interface RandomUsersRepository {
    suspend fun getUsers(): List<User>
}

class RandomUsersRepositoryImp @Inject constructor(
    private val randomUserService: RandomUserService
) : RandomUsersRepository {
    override suspend fun getUsers(): List<User> {
        val results = randomUserService.userList().results

        return results.map {
            User(
                name = "${it.name.title} ${it.name.first} ${it.name.last}",
                nationality = it.nationality,
                thumbnail = it.picture.medium,
            )
        }
    }
}