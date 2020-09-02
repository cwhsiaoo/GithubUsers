package xlet.android.example.githubusers.model

import androidx.recyclerview.widget.DiffUtil
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class User(
    @SerializedName(value = "id")
    @PrimaryKey
    val id: String,

    @SerializedName(value = "avatar_url")
    @ColumnInfo(name = "avatar_url")
    val avatarUrl: String? = null,

    @SerializedName(value = "login")
    @ColumnInfo(name = "login")
    val userName: String? = null,

    @SerializedName(value = "site_admin")
    @ColumnInfo(name = "site_admin")
    val isSiteAdmin: Boolean? = null
) {
    companion object {
        val DIFF_UTIL = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: User, newItem: User) =
                oldItem == newItem
        }
    }
}