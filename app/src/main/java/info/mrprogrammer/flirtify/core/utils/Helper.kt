package info.mrprogrammer.flirtify.core.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.SystemClock
import android.view.View

fun View.setDebouncedOnClickListener(interval: Long = 2000L, onClick: (View) -> Unit) {
    var lastClickTime = 0L
    setOnClickListener { view ->
        val currentTime = SystemClock.elapsedRealtime()
        if (currentTime - lastClickTime >= interval) {
            lastClickTime = currentTime
            onClick(view)
        }
    }
}

fun isConnected(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    if (connectivityManager != null) {
        val info = connectivityManager.allNetworkInfo
        if (info != null) {
            for (networkInfo in info) {
                if (networkInfo.state == NetworkInfo.State.CONNECTED) {
                    return true
                }
            }
        }
    }
    return false
}