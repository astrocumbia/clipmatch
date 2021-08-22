package cc.hubble.network.service

import cc.hubble.network.response.RandomUserListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RandomUserService {

    @GET("api/")
    suspend fun userList(
        @Query("results") results: Int = 100,
        @Query("page") page: Int = 1,
        @Query("seed") seed: String = "Cl1p",
    ): RandomUserListResponse
}