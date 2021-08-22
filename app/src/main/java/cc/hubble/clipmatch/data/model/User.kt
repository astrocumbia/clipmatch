package cc.hubble.clipmatch.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val name: String,
    val nationality: String,
    val thumbnail: String
): Parcelable