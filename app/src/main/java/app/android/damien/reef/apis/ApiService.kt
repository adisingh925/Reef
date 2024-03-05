package app.android.damien.reef.apis

import app.android.damien.reef.model.ApexApiResponse
import app.android.damien.reef.model.addUserRequestBody
import app.android.damien.reef.model.addUserResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @POST("users/add")
    fun addUser(@Body addUserRequestBody: addUserRequestBody): Call<addUserResponseBody>

    @GET("fusion/{nickname}")
    fun getApexData(@Path("nickname") nickname: String): Call<ApexApiResponse>
}