package natto.com.helpshadowperson.service

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import natto.com.helpshadowperson.MainActivity
import natto.com.helpshadowperson.R

class FBFcmListenerService : FirebaseMessagingService() {

    private val TAG = FBFcmListenerService::class.java.simpleName

    override fun onMessageReceived(message: RemoteMessage?) {
        super.onMessageReceived(message)
        message?.let {
            val from = message.from
            val data = message.data

            Log.d(TAG, "from:$from")
            Log.d(TAG, "data:$data")

            val msg = data["data"].toString()
            sendNotification(msg)
        }
    }

    private fun sendNotification(message: String) {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT)

        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE)
        val notificationBuilder = NotificationCompat.Builder(this)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("Push通知のタイトル")
            .setSubText("Push通知のサブタイトル")
            .setAutoCancel(true)
            .setSound(defaultSoundUri)
            .setStyle(NotificationCompat.BigTextStyle().bigText(message))
            .setContentIntent(pendingIntent)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        notificationManager.notify(0, notificationBuilder.build())
    }
}