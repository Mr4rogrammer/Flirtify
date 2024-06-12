package info.mrprogrammer.flirtify.common.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import info.mrprogrammer.flirtify.R
import info.mrprogrammer.ui_manager.ui.compose.permission.utils.getPermissionList
import info.mrprogrammer.ui_manager.ui.compose.permission.utils.isAllPermissionGranter
import info.mrprogrammer.flirtify.common.navigation.consts.NavigationRoute
import info.mrprogrammer.flirtify.features.add.ui.screen.AddPostScreen
import info.mrprogrammer.flirtify.features.dashboard.ui.screen.DashBoardScreen
import info.mrprogrammer.flirtify.features.discovery.ui.screen.DiscoveryScreen
import info.mrprogrammer.flirtify.features.login.ui.screen.LoginScreen
import info.mrprogrammer.flirtify.features.matches.ui.screen.MatcheScreen
import info.mrprogrammer.flirtify.features.messages.ui.screen.MessageScreen
import info.mrprogrammer.flirtify.features.splash.SplashScreen
import info.mrprogrammer.ui_manager.ui.animation.FadeInAnimation
import info.mrprogrammer.ui_manager.ui.compose.permission.PermissionRequestScreen


@Composable
fun AppNavGraph(navHostController: NavHostController, isUserLogedIn: Boolean) {

    NavHost(navController = navHostController, startDestination = NavigationRoute.PERMISSION) {
        composable(NavigationRoute.SPLASH) {
            SplashScreen()
        }

        composable(NavigationRoute.PERMISSION) {
            if (isAllPermissionGranter(LocalContext.current)) {
                navHostController.navigate(NavigationRoute.LOGIN, navOptions = NavOptions.Builder().setPopUpTo(
                    NavigationRoute.PERMISSION, inclusive = true).build())
            } else {
                FadeInAnimation {
                   PermissionRequestScreen(R.raw.permission, getPermissionList()) {
                        navHostController.navigate(NavigationRoute.LOGIN, navOptions = NavOptions.Builder().setPopUpTo(
                            NavigationRoute.PERMISSION, inclusive = true).build())
                    }
                }
            }
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
            FadeInAnimation {
                AddPostScreen()
            }
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