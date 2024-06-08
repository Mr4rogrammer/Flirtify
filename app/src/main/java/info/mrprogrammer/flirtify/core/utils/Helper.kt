package info.mrprogrammer.flirtify.core.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.SystemClock
import android.view.View
import kotlin.random.Random

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


fun getRandomUrlGirlImage(): String {
    val urls = listOf(
        "https://i.pinimg.com/564x/4e/9f/03/4e9f035d05faeb0561835197a51a51f5.jpg",
        "https://i.pinimg.com/564x/b5/00/20/b500200c66f40c45fb35be6353279946.jpg",
        "https://i.pinimg.com/736x/d5/22/a9/d522a9814494de83c33e9d61ecd67161.jpg",
        "https://i.pinimg.com/564x/52/ef/af/52efaf2c0aece1386b01b32a2616bbf7.jpg",
        "https://i.pinimg.com/736x/a3/0e/62/a30e626d721a198407a7ca637af1f368.jpg",
        "https://i.pinimg.com/564x/14/5b/6a/145b6a70249ab6033902bb20b10f8503.jpg",
        "https://i.pinimg.com/564x/ce/0e/25/ce0e25af9e9f8079b00ee5a97b5997d0.jpg",
        "https://i.pinimg.com/736x/05/29/62/05296270abfbae5bac0091f2360d7b4e.jpg",
        "https://i.pinimg.com/474x/e7/1e/5d/e71e5d3ab6a5fcb3a5ef369b521f62b2.jpg",
        "https://i.pinimg.com/474x/bf/ac/ff/bfacff3b62d55ff005d776c060c56c36.jpg"
    )

    // Pick a random URL
    val randomIndex = Random.nextInt(urls.size)
    return urls[randomIndex]
}

fun getRandomGirlName(): String {
    val girlNames = listOf(
        "Sophia", "Emma", "Olivia", "Ava", "Isabella",
        "Mia", "Amelia", "Harper", "Evelyn", "Abigail",
        "Ella", "Lily", "Aria", "Grace", "Chloe",
        "Charlotte", "Aurora", "Riley", "Scarlett", "Victoria"
    )
    return girlNames[Random.nextInt(girlNames.size)]
}