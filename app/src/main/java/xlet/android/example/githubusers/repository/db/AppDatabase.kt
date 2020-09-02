package xlet.android.example.githubusers.repository.db

import androidx.room.Database
import androidx.room.RoomDatabase
import xlet.android.example.githubusers.model.User
import xlet.android.example.githubusers.repository.db.dao.UserDao

@Database(
    entities = [
        User::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}