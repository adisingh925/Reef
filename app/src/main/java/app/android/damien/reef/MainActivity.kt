package app.android.damien.reef

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import app.android.damien.reef.databinding.ActivityMainBinding
import app.android.damien.reef.model.ApexApiResponse
import app.android.damien.reef.model.FocustronicAlkatronicResponse
import app.android.damien.reef.model.FocustronicMastertronicResponse
import app.android.damien.reef.retrofit.ApiClient
import app.android.damien.reef.storage.SharedPreferences
import app.android.damien.reef.utils.AlarmHelper
import app.android.damien.reef.utils.Constants
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        SharedPreferences.init(this)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

//        getApexApiResponse()
//        getFocustronicMastertronicApiResponse()
//        getFocustronicAlkatronicApiResponse()
        AlarmHelper(this).setExactAndAllowWhileIdleAlarm()
    }

//    private fun getFocustronicAlkatronicApiResponse() {
//        CoroutineScope(Dispatchers.IO).launch {
//            val response = ApiClient.apiService.getFocustronicAlkatronicData(
//                SharedPreferences.read(
//                    "nickname",
//                    ""
//                ).toString(),
//                "697"
//            )
//
//            response.enqueue(object : Callback<FocustronicAlkatronicResponse> {
//                override fun onResponse(
//                    call: Call<FocustronicAlkatronicResponse>,
//                    response: Response<FocustronicAlkatronicResponse>
//                ) {
//                    if (response.isSuccessful) {
//                        val data = response.body()
//                        if (data != null) {
//                            Log.d("TAG", "onResponse: $data")
//                            val gson = Gson()
//                            val jsonData = gson.toJson(data)
//                            SharedPreferences.write("focustronicAlkatronicData", jsonData)
//                            SharedPreferences.write("lastUpdatedFocustronicAlkatronic", millisToDateTime(System.currentTimeMillis()))
//                        }
//                    }
//                }
//
//                override fun onFailure(call: Call<FocustronicAlkatronicResponse>, t: Throwable) {
//                    t.printStackTrace()
//                }
//            })
//        }
//    }
//
//    private fun getFocustronicMastertronicApiResponse() {
//        CoroutineScope(Dispatchers.IO).launch {
//            val response = ApiClient.apiService.getFocustronicMastertronicData(
//                SharedPreferences.read(
//                    "nickname",
//                    ""
//                ).toString(),
//                "106"
//            )
//
//            response.enqueue(object : Callback<FocustronicMastertronicResponse> {
//                override fun onResponse(
//                    call: Call<FocustronicMastertronicResponse>,
//                    response: Response<FocustronicMastertronicResponse>
//                ) {
//                    if (response.isSuccessful) {
//                        val data = response.body()
//                        if (data != null) {
//                            Log.d("TAG", "onResponse: $data")
//                            val gson = Gson()
//                            val jsonData = gson.toJson(data)
//                            SharedPreferences.write("focustronicMastertronicData", jsonData)
//                            SharedPreferences.write("lastUpdatedFocustronicMastertronic", millisToDateTime(System.currentTimeMillis()))
//                        }
//                    }
//                }
//
//                override fun onFailure(call: Call<FocustronicMastertronicResponse>, t: Throwable) {
//                    t.printStackTrace()
//                }
//            })
//        }
//    }
//
//    private fun getApexApiResponse() {
//        CoroutineScope(Dispatchers.IO).launch {
//            val response = ApiClient.apiService.getApexData(
//                SharedPreferences.read(
//                    "nickname",
//                    ""
//                ).toString()
//            )
//
//            response.enqueue(object : Callback<ApexApiResponse> {
//                override fun onResponse(
//                    call: Call<ApexApiResponse>,
//                    response: Response<ApexApiResponse>
//                ) {
//                    if (response.isSuccessful) {
//                        val data = response.body()
//                        if (data != null) {
//                            val gson = Gson()
//                            val jsonData = gson.toJson(data)
//                            SharedPreferences.write("apexData", jsonData)
//                            SharedPreferences.write("lastUpdatedApex", millisToDateTime(System.currentTimeMillis()))
//                        }
//                    }
//                }
//
//                override fun onFailure(call: Call<ApexApiResponse>, t: Throwable) {
//                    t.printStackTrace()
//                }
//            })
//        }
//    }

    fun millisToDateTime(millis: Long): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val dateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(millis), ZoneId.systemDefault())
        return dateTime.format(formatter)
    }
}