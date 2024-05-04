package app.android.damien.reef

import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import app.android.damien.reef.databinding.ActivityMainBinding
import app.android.damien.reef.storage.SharedPreferences
import app.android.damien.reef.utils.AlarmHelper
import app.android.damien.reef.utils.Data
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

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

        Data().getApexData()
        Data().getFocustronicResponse()
//        AlarmHelper(this).setExactAndAllowWhileIdleAlarm()
    }
}