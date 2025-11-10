package com.example.impl.presentation.ui.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.impl.R
import com.example.impl.presentation.event.AuthEvent
import com.example.impl.presentation.state.AuthUiState

@Composable
fun LoginScreenContent(
    state: AuthUiState,
    onEvent: (AuthEvent) -> Unit,
    onLoginClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(id = R.string.register),
            fontSize = 28.sp,
            modifier = Modifier
                .padding(top = 100.dp)
                .align(Alignment.Start),
        )
        Spacer(modifier = Modifier.size(28.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = stringResource(R.string.email),
                fontSize = 16.sp
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = state.email,
                onValueChange = { email ->
                    onEvent(AuthEvent.EmailChanged(email))
                },
                placeholder = {
                    stringResource(id = R.string.email)
                },
                label = { Text(text = stringResource(id = R.string.label_email)) },
                shape = RoundedCornerShape(30.dp)
            )
        }

        Spacer(modifier = Modifier.size(16.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = stringResource(R.string.password),
                fontSize = 16.sp
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = state.email,
                onValueChange = { email ->
                    onEvent(AuthEvent.EmailChanged(email))
                },
                placeholder = {
                    stringResource(id = R.string.password)
                },
                label = { Text(text = stringResource(id = R.string.label_password)) },
                shape = RoundedCornerShape(30.dp)
            )
        }

        Spacer(modifier = Modifier.size(16.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = stringResource(R.string.passwor_password),
                fontSize = 16.sp
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = state.email,
                onValueChange = { email ->
                    onEvent(AuthEvent.EmailChanged(email))
                },
                placeholder = {
                    stringResource(id = R.string.label_password_password)
                },
                label = { Text(text = stringResource(id = R.string.label_password_password)) },
                shape = RoundedCornerShape(30.dp)
            )
        }
        Spacer(modifier = Modifier.size(24.dp))
        Button(
            onClick = {},
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(30.dp)
        ) {
            Text(
                text = stringResource(id = R.string.register),
                fontSize = 14.sp
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.exest_account),
                fontSize = 12.sp
            )
            TextButton(
                onClick = { }
            ) {
                Text(
                    text = stringResource(id = R.string.enter),
                    fontSize = 12.sp
                )
            }
        }

        HorizontalDivider()

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = {}) {
                Text(
                    text = "VK"
                )
            }

            Button(onClick = {}) { }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun Preview() {

    val state: AuthUiState = AuthUiState()

    LoginScreenContent(
        state,
        onEvent = {}
    ) { }

}