package app.android.damien.reef

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import app.android.damien.reef.databinding.ActivityMainBinding
import app.android.damien.reef.storage.SharedPreferences
import app.android.damien.reef.utils.AlarmHelper
import app.android.damien.reef.utils.Data
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONArray
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        SharedPreferences.init(this)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        CoroutineScope(Dispatchers.IO).launch {
            Data().getApexData(this)
            Data().getFocustronicResponse(this)
        }.invokeOnCompletion {
            try {
                Data().updateFocustronicWidgetData(this, JSONArray(SharedPreferences.read("focustronicData", "").toString()))
            } catch (e: Exception) {
                Log.e("MainActivity Focustronic Data Update", e.toString())
            }

            try {
                Data().updateApexWidgetsData(this, JSONArray(SharedPreferences.read("apexData", "").toString()))
            } catch (e: Exception) {
                Log.e("MainActivity Apex Data Update", e.toString())
            }
        }

        AlarmHelper(this).setExactAndAllowWhileIdleAlarm()
    }
}