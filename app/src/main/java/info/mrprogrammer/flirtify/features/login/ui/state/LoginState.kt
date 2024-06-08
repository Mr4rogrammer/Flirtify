package info.mrprogrammer.flirtify.features.login.ui.state

sealed class LoginState {
    data object LoginStarted : LoginState()
    data object LoginSuccess : LoginState()
    data object IDLE : LoginState()
}