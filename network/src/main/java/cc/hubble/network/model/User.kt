package cc.hubble.network.model

import com.google.gson.annotations.SerializedName

data class User(
    val gender: String,
    val name: Name,
    val email: String,
    @SerializedName("dob")
    val birthday: Birthday,
    val location: Location,
    val phone: String,
    val cell: String,
    val picture: Picture,
    @SerializedName("nat")
    val nationality: String,
)
