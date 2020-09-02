package xlet.android.example.githubusers.repository.api

import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import xlet.android.example.githubusers.model.User

interface GithubApi {

    @GET("users")
    fun getUserList(
        @Query("since") since: Int = 0
    ): Observable<Response<List<User>>>

    @Headers("Accept: application/vnd.github.v3+json")
    @GET("users")
    fun getFirstUserList(): Single<List<User>>
}