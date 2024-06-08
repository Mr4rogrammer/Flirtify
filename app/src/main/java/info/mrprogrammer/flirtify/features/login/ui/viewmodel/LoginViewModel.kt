package info.mrprogrammer.flirtify.features.login.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavOptions
import dagger.hilt.android.lifecycle.HiltViewModel
import info.mrprogrammer.flirtify.common.navigation.NavInfo
import info.mrprogrammer.flirtify.common.navigation.NavManager
import info.mrprogrammer.flirtify.common.navigation.consts.NavigationRoute
import info.mrprogrammer.flirtify.core.domain.model.SaveDataModel
import info.mrprogrammer.flirtify.core.domain.use_case.SaveData
import info.mrprogrammer.flirtify.features.login.ui.state.LoginState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val saveData: SaveData,
    private val navManager: NavManager
) : ViewModel() {
    private var _loginFlow = MutableStateFlow<LoginState>(LoginState.IDLE)
    val loginFlow = _loginFlow.asStateFlow()

    fun saveUser(user: SaveDataModel?) {
        viewModelScope.launch {
            _loginFlow.value = LoginState.LoginStarted
            if (user != null) {
                saveData(user)
                _loginFlow.value = LoginState.LoginSuccess
                navManager.navigate(
                    NavInfo(id = NavigationRoute.DASHBOARD, navOption = NavOptions.Builder().setPopUpTo(
                        NavigationRoute.LOGIN, inclusive = true).build())
                )
            }
        }
    }

    fun loginStared() {
        _loginFlow.value = LoginState.LoginStarted
    }

    fun loginFailed() {
        _loginFlow.value = LoginState.IDLE
    }
}