package com.example.emoease.screens.authScreen.ui

import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.emoease.R
import com.example.emoease.screens.LoadingOverlay
import com.example.emoease.screens.authScreen.util.LoginScreenViewModel


@Composable
fun SignUpScreen(viewModel: LoginScreenViewModel= hiltViewModel()) {
    val isLoading = remember {
        mutableStateOf(false)
    }
    LoadingOverlay(isLoading = isLoading.value) {
        Column(modifier = Modifier
            .fillMaxSize()) {
            HeaderAuth()
            UserSignUpForm(viewModel = viewModel, isLoading)
        }
    }

}

@Composable
fun UserSignUpForm(viewModel: LoginScreenViewModel, isLoading: MutableState<Boolean>) {
    val name = remember {
        mutableStateOf("")
    }
    val email = remember {
        mutableStateOf("")
    }
    val phoneNo = remember {
        mutableStateOf("")
    }
    val passwordState = remember {
        mutableStateOf("")
    }
    val confirmPasswordState = remember {
        mutableStateOf("")
    }
    val passwordVisibility = remember {
        mutableStateOf(false)
    }
    val passwordVisibilityConfirm = remember {
        mutableStateOf(false)
    }
    val emailRequest = FocusRequester.Default
    val passwordRequest = FocusRequester.Default
    val phoneRequester = FocusRequester.Default
    val confirmPasswordRequest = FocusRequester.Default
    val valid = remember(email.value, passwordState.value, phoneNo.value, name.value) {
        email.value.trim().isNotEmpty() && passwordState.value.trim()
            .isNotEmpty() && name.value.trim().isNotEmpty() && phoneNo.value.trim().isNotEmpty()
    }

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 8.dp)
        .verticalScroll(rememberScrollState())) {
        NameInputTf(nameState = name, onAction = KeyboardActions {
            emailRequest.requestFocus()
        })
        EmailInput(
            emailState = email,
            modifier = Modifier.focusRequester(emailRequest),
            onAction = KeyboardActions {
                phoneRequester.requestFocus()
            })
        PhoneNumberInput(emailState = phoneNo, modifier = Modifier.focusRequester(phoneRequester),
            onAction = KeyboardActions {
                passwordRequest.requestFocus()
            })
        PasswordInput(
            modifier = Modifier.focusRequester(passwordRequest),
            passwordState = passwordState,
            labelId = stringResource(id = R.string.password),
            enabled = true,
            passwordVisibility = passwordVisibility,
            onAction = KeyboardActions {
                confirmPasswordRequest.requestFocus()
            }
        )
        PasswordInput(
            modifier = Modifier.focusRequester(confirmPasswordRequest),
            passwordState = confirmPasswordState,
            labelId = stringResource(id = R.string.confirm_password),
            enabled = true,
            passwordVisibility = passwordVisibilityConfirm
        )
        SubmitButton(
            textId = stringResource(id = R.string.sign_up),
            loading = false,
            validInputs = valid
        ) {
        isLoading.value=true
        }
    }

}
