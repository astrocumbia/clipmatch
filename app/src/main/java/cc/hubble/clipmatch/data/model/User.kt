package cc.hubble.clipmatch.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val firstName: String,
    val fullName: String,
    val age: Int,
    val email: String,
    val phone: String,
    val gender: String,
    val birthday: String,
    val city: String,
    val nationality: String,
    val thumbnail: String,
): Parcelable