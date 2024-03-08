package app.android.damien.reef.apis

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

    @GET("fusion/{nickname}")
    fun getApexData(@Path("nickname") nickname: String): Call<ApexApiResponse>

    @GET("focustronic/impromptu-reef/mastertronic/{nickname}")
    fun getFocustronicMastertronicData(@Path("nickname") nickname: String): Call<FocustronicMastertronicResponse>

    @GET("focustronic/impromptu-reef/alkatronic/{nickname}")
    fun getFocustronicAlkatronicData(@Path("nickname") nickname: String): Call<FocustronicAlkatronicResponse>
}