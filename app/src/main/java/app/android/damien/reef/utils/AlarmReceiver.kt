package app.android.damien.reef.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi

class AlarmReceiver : BroadcastReceiver() {
    @RequiresApi(Build.VERSION_CODES.S)
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d("AlarmReceiver", "Alarm received")
        if (context != null) {
            AlarmHelper(context).setExactAndAllowWhileIdleAlarm()
        }
    }
}