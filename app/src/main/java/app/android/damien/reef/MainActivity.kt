package app.android.damien.reef

import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import app.android.damien.reef.databinding.ActivityMainBinding
import app.android.damien.reef.model.ApexApiResponse
import app.android.damien.reef.retrofit.ApiClient
import app.android.damien.reef.storage.SharedPreferences
import app.android.damien.reef.utils.Constants
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        SharedPreferences.init(this)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        getApexApiResponse()
    }

    private fun getApexApiResponse() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = ApiClient.apiService.getApexData(
                SharedPreferences.read(
                    Constants.APEX.toString() + "nickname",
                    ""
                ).toString()
            )

            response.enqueue(object : Callback<ApexApiResponse> {
                override fun onResponse(
                    call: Call<ApexApiResponse>,
                    response: Response<ApexApiResponse>
                ) {
                    if (response.isSuccessful) {
                        val data = response.body()
                        if (data != null) {
                            val gson = Gson()
                            val jsonData = gson.toJson(data)
                            SharedPreferences.write("apexData", jsonData)
                        }
                    }
                }

                override fun onFailure(call: Call<ApexApiResponse>, t: Throwable) {
                    t.printStackTrace()
                }
            })
        }
    }
}