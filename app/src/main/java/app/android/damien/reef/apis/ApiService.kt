package app.android.damien.reef.apis

import app.android.damien.reef.model.alkatronic.AlkatronicData
import app.android.damien.reef.model.aquariumdevices.AquariumDevices
import app.android.damien.reef.model.aquariumdevices.AquariumTanks
import app.android.damien.reef.model.login.LoginRequest
import app.android.damien.reef.model.login.LoginResponse
import app.android.damien.reef.model.apex.ApexData
import app.android.damien.reef.model.mastertronic.MastertronicData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    /**
     * Apex APIs
     */

    @POST("${CUSTOM_BASE_URL}cookies/fusion")
    fun apexUserLogin(
        @Body request: LoginRequest,
    ): Call<LoginResponse>

    @POST("${CUSTOM_BASE_URL}cookies/fusion/refresh")
    fun apexRefreshCookie(
        @Body request: LoginRequest
    ): Call<LoginResponse>

    @GET("${APEX_BASE_URL}apex?page=1&per_page=20&total_pages=1&total_entries=1")
    fun getApexData(
        @Header("Cookie") cookie: String,
    ): Call<ApexData>

    /**
     * Focustronic APIs
     */

    @POST("${CUSTOM_BASE_URL}cookies/focustronic")
    fun focustronicUserLogin(
        @Body request: LoginRequest,
    ): Call<LoginResponse>

    @POST("${CUSTOM_BASE_URL}cookies/focustronic/refresh")
    fun focustronicRefreshCookie(
        @Body request: LoginRequest
    ): Call<LoginResponse>

    @GET("${ALKATRONIC_BASE_URL}/api/v2/aquarium-tanks")
    fun getAquariumTanks(
        @Header("x-session-token") token: String,
    ): Call<AquariumTanks>

    @GET("${ALKATRONIC_BASE_URL}/api/v2/aquarium-tanks/{id}")
    fun getAquariumDevices(
        @Header("x-session-token") token: String,
        @Path("id") tankId: Int
    ): Call<AquariumDevices>

    @GET("${ALKATRONIC_BASE_URL}/api/v2/devices/mastertronic/{id}/parameter-information")
    fun getMastertronicData(
        @Header("x-session-token") token: String,
        @Path("id") tankId: Int
    ): Call<MastertronicData>

    @GET("${ALKATRONIC_BASE_URL}/api/v2/devices/alkatronic/{id}/data/test-records?day=1")
    fun getAlkatronicData(
        @Header("x-session-token") token: String,
        @Path("id") tankId: Int
    ): Call<AlkatronicData>

    companion object{
        const val CUSTOM_BASE_URL = "https://www.impromptureef.co.uk/api/"
        const val ALKATRONIC_BASE_URL = "https://alkatronic.focustronic.com"
        const val APEX_BASE_URL = "https://apexfusion.com/api/"
    }
}