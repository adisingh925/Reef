package app.android.damien.reef.apis

import app.android.damien.reef.model.AddApexUserRequest
import app.android.damien.reef.model.AddFocustronicUserRequest
import app.android.damien.reef.model.AddFocustronicUserResponse
import app.android.damien.reef.model.ApexApiResponse
import app.android.damien.reef.model.FocustronicAlkatronicResponse
import app.android.damien.reef.model.FocustronicMastertronicResponse
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

    @POST("fusion/{nickname}")
    fun addApexAccount(@Path("nickname") nickname: String, @Body addApexUserRequest: AddApexUserRequest): Call<addUserResponseBody>

    @GET("fusion/{nickname}")
    fun getApexData(@Path("nickname") nickname: String): Call<ApexApiResponse>

    @POST("focustronic/{nickname}")
    fun addFocustronicAccount(@Path("nickname") nickname: String, @Body addFocustronicUserRequest: AddFocustronicUserRequest): Call<AddFocustronicUserResponse>

    @GET("focustronic/{nickname}/mastertronic/{deviceId}")
    fun getFocustronicMastertronicData(@Path("nickname") nickname: String, @Path("deviceId") deviceId : String): Call<FocustronicMastertronicResponse>

    @GET("focustronic/{nickname}/alkatronic/{deviceId}")
    fun getFocustronicAlkatronicData(@Path("nickname") nickname: String,  @Path("deviceId") deviceId : String): Call<FocustronicAlkatronicResponse>
}