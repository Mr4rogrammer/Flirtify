package info.mrprogrammer.flirtify.common.navigation

import androidx.navigation.NavOptions
import info.mrprogrammer.flirtify.common.navigation.consts.NavigationRoute
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NavManager @Inject constructor() {
    private val _routeInfo = MutableStateFlow(NavInfo())
    val routeInfo: StateFlow<NavInfo> = _routeInfo

    fun navigate(routeInfo: NavInfo?) {
        _routeInfo.update { routeInfo ?: NavInfo() }
    }

    fun navigate(id: Long, popBackUp:Boolean = false) {
       val navInfo =  when (id) {
            0L -> {
                NavInfo(id = NavigationRoute.DASHBOARD)
            }

            1L -> {
                NavInfo(id = NavigationRoute.DISCOVERY)
            }

            2L -> {
                NavInfo(id = NavigationRoute.ADD)
            }

            3L -> {
                NavInfo(id = NavigationRoute.MATCHES)
            }

            4L -> {
                NavInfo(id = NavigationRoute.MESSAGE)
            }

           else -> {
               NavInfo(id = NavigationRoute.DASHBOARD)
           }
       }
       if (popBackUp) {
           navInfo.navOption = NavOptions.Builder().setPopUpTo(
               NavigationRoute.LOGIN, inclusive = true).build()
       }
        navigate(navInfo)
    }
}

data class NavInfo(val id: String? = null, var navOption: NavOptions? = null)