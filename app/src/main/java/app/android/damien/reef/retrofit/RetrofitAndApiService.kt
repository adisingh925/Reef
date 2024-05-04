package app.android.damien.reef.retrofit

import app.android.damien.reef.apis.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    val apexRetrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://apexfusion.com/api/")
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