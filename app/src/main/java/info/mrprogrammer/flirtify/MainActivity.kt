package info.mrprogrammer.flirtify

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import info.mrprogrammer.flirtify.common.navigation.AppNavGraph
import info.mrprogrammer.ui_manager.ui.theme.FlirtifyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FlirtifyTheme {
                SetAppNavGraph()
            }
        }
    }

    @Composable
    fun SetAppNavGraph() {
        val navHostController = rememberNavController()
        AppNavGraph(navHostController = navHostController)
    }
}
