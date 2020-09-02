package xlet.android.example.githubusers.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.LoadType.*
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import okhttp3.HttpUrl
import retrofit2.HttpException
import xlet.android.example.githubusers.model.User
import xlet.android.example.githubusers.repository.api.GithubApi
import xlet.android.example.githubusers.repository.api.PageLinks
import xlet.android.example.githubusers.repository.db.AppDatabase
import java.io.IOException

@ExperimentalPagingApi
class UserRemoteMediator(
    private val _githubApi: GithubApi,
    private val _db: AppDatabase
) : RemoteMediator<Int, User>() {
    var nextSince = 0

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, User>
    ): MediatorResult {
        try {
            when (loadType) {
                PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                REFRESH -> nextSince = 0
                APPEND -> if (nextSince >= MAX_QUERY_USER) {
                    return MediatorResult.Success(endOfPaginationReached = true)
                }
            }
            _githubApi.getUserList(
                since = nextSince
            ).apply {
                if (!isSuccessful) {
                    return MediatorResult.Error(HttpException(this))
                }
                nextSince = PageLinks.parsePageLinks(this).next?.run {
                    HttpUrl.parse(this)
                }?.queryParameter("since")
                    ?.toIntOrNull() ?: 0

                _db.withTransaction {
                    _db.userDao().let { userDao ->
                        if (loadType == REFRESH) {
                            userDao.clearAllUsers()
                        }
                        body()?.takeIf { it.isNotEmpty() }?.run {
                            userDao.insert(this)
                        }
                    }
                }
                return MediatorResult.Success(endOfPaginationReached = body().isNullOrEmpty())
            }
        } catch (e: IOException) {
            return MediatorResult.Error(e)
        } catch (e: HttpException) {
            return MediatorResult.Error(e)
        }
    }

    companion object {
        private const val MAX_QUERY_USER = 100
    }
}