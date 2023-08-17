package com.example.emoease.screens.authScreen.ui


import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.emoease.EmoEaseApp
import com.example.emoease.R
import com.example.emoease.roomDb.AuthSharedPreferences
import com.example.emoease.screens.CustomSnackBar
import com.example.emoease.screens.LogoApp
import com.example.emoease.screens.StatusBarColor
import com.example.emoease.screens.authScreen.util.LoginScreenViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import timber.log.Timber


@Preview
@Composable
fun LoginScreen(loginScreenViewModel: LoginScreenViewModel = hiltViewModel()) {
    Log.d("TAG", "LoginScreen: ${AuthSharedPreferences.refreshToken} ${System.currentTimeMillis()} ${EmoEaseApp.authToken}")
    val showLoginForm = rememberSaveable {
        mutableStateOf(true)
    }
    val isLoading = remember {
        mutableStateOf(false)
    }
    val errorText = remember {
        mutableStateOf("")
    }
    val showSnackBar = remember {
        mutableStateOf(false)
    }
    StatusBarColor(color = MaterialTheme.colors.primary)
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            HeaderAuth()
            val context = LocalContext.current
            //ReaderAppLogo(modifier = Modifier.padding(top=30.dp))

            //if to show login screen else sign up
            if (showLoginForm.value) UserForm(
                loading = isLoading.value, isCreateAccount = false
            ) { email, pwd ->
                //TODO login
                loginScreenViewModel.signInWithEmailAndPassword(email=email, password = pwd, home = {
                   //navigate
                }){
                    errorText.value=it
                    showSnackBar.value=true
                }
            }
            else {
                UserForm(loading = isLoading.value, isCreateAccount = true) { email, pwd ->
                    //TODO create account
                    loginScreenViewModel.createUserWithEmailAndPassword(email=email, password = pwd, home = {
                       //navigate
                    }){
                        errorText.value=it
                        showSnackBar.value=true
                    }

                }
            }
            Spacer(modifier = Modifier.height(15.dp))
            CustomDivider()
            Spacer(modifier = Modifier.height(15.dp))
            GoogleSignInButton(onSignInSuccess = {
                val credential = GoogleAuthProvider.getCredential(it.idToken, null)
                FirebaseAuth.getInstance().signInWithCredential(credential)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            //TODO : sign in with email
                            Timber.tag("googlesign").d("Signin complete ")
                        }
                    }

            }, onSignInFailure = {
                Timber.tag("googlesign").e("error")
            })
            Spacer(modifier = Modifier.height(15.dp))
            Row(
                modifier = Modifier.padding(15.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                val text = if (showLoginForm.value) "Sign up" else "Login"
                Text(
                    text = "New User?",
//                    style = ReaderAppTextStyle.subText
                )
                Text(text, modifier = Modifier
                    .clickable {
                        showLoginForm.value = !showLoginForm.value

                    }
                    .padding(start = 5.dp), fontWeight = FontWeight.Bold,
//                    style = ReaderAppTextStyle.subText,
                    color = MaterialTheme.colors.secondaryVariant)
            }
        }
        CustomSnackBar(text = errorText.value,showSnackBar=showSnackBar)
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun UserForm(
    loading: Boolean = false,
    isCreateAccount: Boolean = false,
    onDone: (String, String) -> Unit = { email, pwd -> }
) {
    val email = rememberSaveable {
        mutableStateOf("")
    }
    val password = rememberSaveable {
        mutableStateOf("")
    }
    val passwordVisibility = rememberSaveable {
        mutableStateOf(false)
    }
    val passwordFocusRequest = FocusRequester.Default
    val keyboardController = LocalSoftwareKeyboardController.current
    val valid = remember(email.value, password.value) {
        email.value.trim().isNotEmpty() && password.value.trim().isNotEmpty()
    }
    val modifier = Modifier
        .wrapContentSize()
        .padding(horizontal = 8.dp)
//        .background(ExtraLightReaderColor)
        .verticalScroll(rememberScrollState())
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        PhoneNumberInput(emailState = email, enabled = !loading, onAction = KeyboardActions {
            passwordFocusRequest.requestFocus()
        })
        PasswordInput(modifier = Modifier.focusRequester(passwordFocusRequest),
            passwordState = password,
            labelId = "Password",
            enabled = !loading,
            passwordVisibility = passwordVisibility,
            onAction = KeyboardActions {
                if (!valid) return@KeyboardActions
                onDone(email.value.trim(), password.value.trim())
                keyboardController?.hide()
            })
        SubmitButton(
            textId = stringResource(id = R.string.login),
            loading = loading,
            validInputs = valid
        ) {
            onDone(email.value.trim(), password.value.trim())
            keyboardController?.hide()
        }
    }
}

@Composable
fun HeaderAuth() {
    Card(
        modifier = Modifier
            .height(200.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(bottomStart = 50.dp, bottomEnd = 50.dp),
        backgroundColor = MaterialTheme.colors.primary,
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp), contentAlignment = Alignment.Center
        ) {
            LogoApp()
        }

    }
}
