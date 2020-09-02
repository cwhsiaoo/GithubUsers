package xlet.android.example.githubusers.repository.api

import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import xlet.android.example.githubusers.model.User

interface GithubApi {

    @GET("users")
    fun getUserList(
        @Query("since") since: Int = 0
    ): Observable<Response<List<User>>>
}