package xlet.android.example.githubusers.repository.db.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import xlet.android.example.githubusers.model.User

@Dao
interface UserDao {

    @Query("SELECT * FROM user ORDER BY id ASC")
    fun allUsers(): PagingSource<Int, User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(users: List<User>): List<Long>

    @Query("DELETE FROM user")
    suspend fun clearAllUsers()
}