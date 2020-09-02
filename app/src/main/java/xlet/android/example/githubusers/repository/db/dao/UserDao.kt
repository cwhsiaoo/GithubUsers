package xlet.android.example.githubusers.repository.db.dao

import androidx.room.Dao
import androidx.room.Query
import xlet.android.example.githubusers.model.User

@Dao
interface UserDao {
    @Query("SELECT * FROM user ORDER BY id ASC")
    fun getAllUsers(): List<User>
}