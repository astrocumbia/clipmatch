package cc.hubble.clipmatch.core

import cc.hubble.network.model.User

fun User.toModel(): cc.hubble.clipmatch.data.model.User {
    return cc.hubble.clipmatch.data.model.User(
        fullName = "${name.title} ${name.first} ${name.last}",
        age = birthday.age,
        email = email,
        phone = phone,
        gender = gender,
        birthday = birthday.date,
        city = "${location.state}, ${location.country}",
        nationality = location.country,
        thumbnail = picture.medium,
    )
}