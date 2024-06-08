package info.mrprogrammer.flirtify.common.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.firebase.auth.FirebaseAuth
import info.mrprogrammer.flirtify.common.navigation.consts.NavigationRoute
import info.mrprogrammer.flirtify.features.dashboard.ui.DashBoardScreen
import info.mrprogrammer.flirtify.features.login.ui.LoginScreen
import info.mrprogrammer.flirtify.features.login.ui.viewmodel.LoginViewModel
import info.mrprogrammer.flirtify.features.splash.SplashScreen
import info.mrprogrammer.ui_manager.ui.animation.FadeInAnimation


@Composable
fun AppNavGraph(navHostController: NavHostController) {

    NavHost(navController = navHostController, startDestination = NavigationRoute.LOGIN) {
        composable(NavigationRoute.SPLASH) {
            SplashScreen()
        }

        composable(NavigationRoute.LOGIN) {
            val isUserLogedIn = FirebaseAuth.getInstance().currentUser
            if (isUserLogedIn == null) {
                FadeInAnimation {
                    LoginScreen()
                }
            } else {
                FadeInAnimation {
                    DashBoardScreen()
                }
            }

        }

        composable(NavigationRoute.MATCHES) {
            FadeInAnimation {
                LoginScreen()
            }
        }

        composable(NavigationRoute.ADD) {
            FadeInAnimation {
                LoginScreen()
            }
        }

        composable(NavigationRoute.DASHBOARD) {
            FadeInAnimation {
                DashBoardScreen()
            }
        }

        composable(NavigationRoute.DISCOVERY) {
            FadeInAnimation {
                LoginScreen()
            }
        }

        composable(NavigationRoute.MESSAGE) {
            FadeInAnimation {
                LoginScreen()
            }
        }
    }
}