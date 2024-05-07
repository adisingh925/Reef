package app.android.damien.reef.retrofit

import android.util.Log
import app.android.damien.reef.model.login.LoginRequest
import app.android.damien.reef.storage.SharedPreferences
import app.android.damien.reef.utils.Constants
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class Authenticator : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        val requestUrl = response.request().url()
        val baseUrl = requestUrl.host()

        if (baseUrl.equals("apexfusion.com")) {
            Log.d("Authenticator", "apexfusion.com")

            val refreshRequest = ApiClient.customApiService.apexRefreshCookie(
                LoginRequest(
                    SharedPreferences.read(
                        "${Constants.APEX}username",
                        ""
                    ).toString(),
                    SharedPreferences.read("${Constants.APEX}password", "").toString()
                )
            )

            val result = refreshRequest.execute()

            if (result.isSuccessful) {
                val newCookie = result.body()?.sid
                SharedPreferences.write("${Constants.APEX}cookie", newCookie.toString())

                return response
                    .request()
                    .newBuilder()
                    .header("Cookie", "connect.sid=${newCookie}")
                    .build()
            }
        } else if (baseUrl.equals("alkatronic.focustronic.com")) {
            Log.d("Authenticator", "alkatronic.focustronic.com")
            val refreshRequest = ApiClient.customApiService.focustronicRefreshCookie(
                LoginRequest(
                    SharedPreferences.read(
                        "${Constants.FOCUSTRONIC}username",
                        ""
                    ).toString(),
                    SharedPreferences.read("${Constants.FOCUSTRONIC}password", "").toString()
                )
            )

            val result = refreshRequest.execute()

            if (result.isSuccessful) {
                val newCookie = result.body()?.sid
                SharedPreferences.write("${Constants.FOCUSTRONIC}cookie", newCookie.toString())

                return response
                    .request()
                    .newBuilder()
                    .header("x-session-token", newCookie.toString())
                    .build()
            }
        } else {
            Log.d("Authenticator", "Unknown URL: $baseUrl")
        }


        return null
    }
}