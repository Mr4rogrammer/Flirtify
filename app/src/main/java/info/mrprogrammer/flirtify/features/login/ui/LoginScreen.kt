package info.mrprogrammer.flirtify.features.login.ui

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import info.mrprogrammer.flirtify.R
import info.mrprogrammer.flirtify.core.domain.model.SaveDataModel
import info.mrprogrammer.flirtify.features.login.ui.state.LoginState
import info.mrprogrammer.flirtify.features.login.ui.viewmodel.LoginViewModel
import info.mrprogrammer.ui_manager.ui.compose.lotfiles.AnimatedPreloader

@Composable
fun LoginScreen(viewModel: LoginViewModel = hiltViewModel()) {
    val context = LocalContext.current
    val auth = FirebaseAuth.getInstance()
    val loginState by viewModel.loginFlow.collectAsState()

    when (loginState) {
        LoginState.IDLE -> {

        }

        LoginState.LoginSuccess -> {

        }

        LoginState.LoginStarted -> {

        }
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
        try {
            val account = task.result
            val credential = GoogleAuthProvider.getCredential(account.idToken, null)
            auth.signInWithCredential(credential)
                .addOnCompleteListener { signInTask ->
                    if (signInTask.isSuccessful) {
                        val user = auth.currentUser
                        val valueMap = hashMapOf<String, Any?>(
                            "name" to user?.displayName.toString(),
                            "email" to user?.email.toString(),
                            "photo" to user?.photoUrl.toString()
                        )
                        val saveDaModel = SaveDataModel("")
                        saveDaModel.key = "User"
                        saveDaModel.value = valueMap
                        viewModel.saveUser(saveDaModel)
                    } else {
                        viewModel.loginFailed()
                        Toast.makeText(context, "Login Failed", Toast.LENGTH_SHORT).show()
                    }
                }
        } catch (e: Exception) {
            viewModel.loginFailed()
            Toast.makeText(context, "Login Failed", Toast.LENGTH_SHORT).show()
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(painter = painterResource(id = R.drawable.login_banner), contentDescription = "")
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = stringResource(id = R.string.let_meeting_new_peoples),
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(100.dp))
            Surface(modifier = Modifier
                .fillMaxWidth(.8f)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) {
                    if (loginState == LoginState.IDLE) {
                        viewModel.loginStared()
                        signInWithGoogle(context, launcher)
                    }
                }
                .padding(16.dp)
                .clip(RoundedCornerShape(30.dp)),
                color = MaterialTheme.colorScheme.background) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(10.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.google),
                        contentDescription = "",
                        modifier = Modifier.height(30.dp)
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = stringResource(id = R.string.login_with_google),
                            color = MaterialTheme.colorScheme.primary,
                            style = MaterialTheme.typography.titleMedium,
                            fontSize = 18.sp
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = stringResource(id = R.string.teams_and_conditions),
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Black,
                fontSize = 13.sp,
            )
        }

        if (loginState == LoginState.LoginStarted) {
            AnimatedPreloader(
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxWidth()
                    .fillMaxHeight(0.09f), rawId = R.raw.loading
            )
        }
    }
}

fun signInWithGoogle(
    context: Context,
    launcher: ManagedActivityResultLauncher<Intent, ActivityResult>
) {
    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken(context.getString(R.string.default_web_client_id))
        .requestEmail()
        .build()

    val googleSignInClient = GoogleSignIn.getClient(context, gso)
    val signInIntent = googleSignInClient.signInIntent
    launcher.launch(signInIntent)
}

