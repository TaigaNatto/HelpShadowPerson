package natto.com.helpshadowperson.service

import android.util.Log
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.FirebaseInstanceIdService


class FBInstanceIDListenerService : FirebaseInstanceIdService() {
    private val TAG = FBInstanceIDListenerService::class.java.simpleName

    override fun onTokenRefresh() {
        //ここで取得したInstanceIDをサーバー管理者に伝える

        val refreshedToken = FirebaseInstanceId.getInstance().token
        Log.d("AAAA", "Refreshed token: " + refreshedToken!!)
    }
}