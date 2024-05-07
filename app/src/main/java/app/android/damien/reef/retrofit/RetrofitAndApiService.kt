package app.android.damien.reef.retrofit

import app.android.damien.reef.apis.ApiService
import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {

    private val client: OkHttpClient = OkHttpClient().newBuilder()
        .authenticator(Authenticator())
        .retryOnConnectionFailure(true)
        .connectionSpecs(listOf(ConnectionSpec.MODERN_TLS))
        .build()

    val apexRetrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://apexfusion.com/api/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val customRetrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://www.impromptureef.co.uk/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val alkatronicRetrofit: Retrofit by lazy {
        Retrofit.Builder()
            .client(client)
            .baseUrl("https://alkatronic.focustronic.com/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}

object ApiClient {
    val apexApiService: ApiService by lazy {
        RetrofitClient.apexRetrofit.create(ApiService::class.java)
    }

    val customApiService: ApiService by lazy {
        RetrofitClient.customRetrofit.create(ApiService::class.java)
    }

    val alkatronicApiService: ApiService by lazy {
        RetrofitClient.alkatronicRetrofit.create(ApiService::class.java)
    }
}