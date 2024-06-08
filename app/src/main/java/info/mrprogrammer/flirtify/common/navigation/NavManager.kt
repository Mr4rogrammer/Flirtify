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

    fun navigate(id: Long) {
        when (id) {
            0L -> {
                navigate(
                    NavInfo(id = NavigationRoute.DASHBOARD)
                )
            }

            1L -> {
                navigate(
                    NavInfo(id = NavigationRoute.DISCOVERY)
                )
            }

            2L -> {
                navigate(
                    NavInfo(id = NavigationRoute.ADD)
                )
            }

            3L -> {
                navigate(
                    NavInfo(id = NavigationRoute.MATCHES)
                )
            }

            4L -> {
                navigate(
                    NavInfo(id = NavigationRoute.MESSAGE)
                )
            }
        }
    }
}

data class NavInfo(val id: String? = null, val navOption: NavOptions? = null)