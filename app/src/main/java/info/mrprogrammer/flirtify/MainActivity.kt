package info.mrprogrammer.flirtify

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import info.mrprogrammer.flirtify.common.navigation.AppNavGraph
import info.mrprogrammer.flirtify.common.navigation.NavManager
import info.mrprogrammer.flirtify.common.navigation.consts.NavigationRoute
import info.mrprogrammer.ui_manager.ui.theme.FlirtifyTheme
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var navigationManager: NavManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.statusBarColor = getColor(R.color.white)
        window.decorView.systemUiVisibility =
            window.decorView.systemUiVisibility or android.view.View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        setContent {
            FlirtifyTheme {
                SetAppNavGraph()
            }
        }
    }

    @Composable
    fun SetAppNavGraph() {
        val isUserLogedIn = FirebaseAuth.getInstance().currentUser != null

        val navHostController = rememberNavController()
        AppNavGraph(navHostController = navHostController, isUserLogedIn)

        val navInfo by navigationManager.routeInfo.collectAsState()
        LaunchedEffect(key1 = navInfo) {
            navInfo.id?.let {
                if (it == NavigationRoute.SPLASH) {
                    navHostController.navigateUp()
                    navigationManager.navigate(null)
                    return@let
                }
                navHostController.navigate(it, navOptions = navInfo.navOption)
                navigationManager.navigate(null)
            }
        }
    }
}
