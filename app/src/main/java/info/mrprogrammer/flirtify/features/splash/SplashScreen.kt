package info.mrprogrammer.flirtify.features.splash

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun SplashScreen() {
    Scaffold {
        Text(text = "Splash Screen", modifier = Modifier.padding(it), color = Color.Red)
    }
}