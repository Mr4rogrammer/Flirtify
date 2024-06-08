package info.mrprogrammer.ui_manager.ui.compose.lotfiles

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun AnimatedPreloader(modifier: Modifier = Modifier, rawId: Int) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(rawId))
    val progress by animateLottieCompositionAsState(
        composition, iterations = 2000
    )
    LottieAnimation(
        composition,
        progress,
        modifier = modifier
    )
}

