package app.android.damien.reef.apis

import app.android.damien.reef.model.addUserRequestBody
import app.android.damien.reef.model.addUserResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("users/add")
    fun addUser(@Body addUserRequestBody: addUserRequestBody): Call<addUserResponseBody>
}