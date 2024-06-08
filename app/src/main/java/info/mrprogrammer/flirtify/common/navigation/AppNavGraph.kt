package info.mrprogrammer.flirtify.common.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import info.mrprogrammer.flirtify.common.navigation.consts.NavigationRoute
import info.mrprogrammer.flirtify.features.dashboard.ui.DashBoardScreen
import info.mrprogrammer.flirtify.features.discovery.ui.DiscoveryScreen
import info.mrprogrammer.flirtify.features.login.ui.LoginScreen
import info.mrprogrammer.flirtify.features.matches.ui.MatcheScreen
import info.mrprogrammer.flirtify.features.messages.ui.MessageScreen
import info.mrprogrammer.flirtify.features.splash.SplashScreen
import info.mrprogrammer.ui_manager.ui.animation.FadeInAnimation


@Composable
fun AppNavGraph(navHostController: NavHostController, isUserLogedIn:Boolean) {

    NavHost(navController = navHostController, startDestination = NavigationRoute.LOGIN) {
        composable(NavigationRoute.SPLASH) {
            SplashScreen()
        }

        composable(NavigationRoute.LOGIN) {
            if (isUserLogedIn) {
                FadeInAnimation {
                    DashBoardScreen()
                }
            } else {
                FadeInAnimation {
                    LoginScreen()
                }
            }

        }

        composable(NavigationRoute.MATCHES) {
            FadeInAnimation {
                MatcheScreen()
            }
        }

        composable(NavigationRoute.ADD) {

        }

        composable(NavigationRoute.DASHBOARD) {
            FadeInAnimation {
                DashBoardScreen()
            }
        }

        composable(NavigationRoute.DISCOVERY) {
            FadeInAnimation {
                DiscoveryScreen()
            }
        }

        composable(NavigationRoute.MESSAGE) {
            FadeInAnimation {
                MessageScreen()
            }
        }
    }
}