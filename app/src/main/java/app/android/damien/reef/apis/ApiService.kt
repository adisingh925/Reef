package app.android.damien.reef.apis

import app.android.damien.reef.model.LoginRequest
import app.android.damien.reef.model.LoginResponse
import app.android.damien.reef.model.apex.ApexData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {

    /**
     * Apex APIs
     */

    @POST("https://www.impromptureef.co.uk/api/cookies/fusion")
    fun apexUserLogin(
        @Body request: LoginRequest,
    ): Call<LoginResponse>

    @POST("https://www.impromptureef.co.uk/api/cookies/fusion/refresh")
    fun apexRefreshCookie(
        @Body request: LoginRequest
    ): Call<LoginResponse>

    @GET("https://apexfusion.com/api/apex?page=1&per_page=20&total_pages=1&total_entries=1")
    fun getApexData(
        @Header("Cookie") cookie: String,
        @Header("x-requested-with") xRequestedWith: String = "XMLHttpRequest",
    ): Call<ApexData>

    /**
     * Focustronic APIs
     */

    @POST("https://www.impromptureef.co.uk/api/cookies/focustronic")
    fun focustronicUserLogin(
        @Body request: LoginRequest,
    ): Call<LoginResponse>

}