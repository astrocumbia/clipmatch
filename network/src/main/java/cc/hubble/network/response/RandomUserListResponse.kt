package cc.hubble.network.response

import cc.hubble.network.model.Info
import cc.hubble.network.model.User

data class RandomUserListResponse(
    val results: List<User>,
    val info: Info
)