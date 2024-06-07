package info.mrprogrammer.flirtify.common.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import info.mrprogrammer.flirtify.common.navigation.consts.NavigationRoute
import info.mrprogrammer.flirtify.features.splash.SplashScreen


@Composable
fun AppNavGraph(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = NavigationRoute.SPLASH) {
        composable(NavigationRoute.SPLASH) {
            SplashScreen()
        }

        composable(NavigationRoute.LOGIN) {

        }

        composable(NavigationRoute.MATCHES) {

        }

        composable(NavigationRoute.MATCHES_DETAILS) {

        }

        composable(NavigationRoute.DASHBOARD) {

        }

        composable(NavigationRoute.DISCOVERY) {

        }

        composable(NavigationRoute.MESSAGE) {

        }
    }
}