package com.example.impl.presentation.ui.register

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core.R as coreR
import com.example.impl.R
import com.example.impl.presentation.event.AuthEvent
import com.example.impl.presentation.state.AuthUiState
import com.example.impl.utils.ui.AuthField

@Composable
fun RegistrationScreenContent(
    state: AuthUiState,
    onEvent: (AuthEvent) -> Unit,
    onRegisterClick: () -> Unit,
    onLoginClick: () -> Unit = {},
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = coreR.color.screen_background))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(id = R.string.register),
            fontSize = 28.sp,
            modifier = Modifier
                .padding(top = 100.dp)
                .align(Alignment.Start),
            color = colorResource(id = coreR.color.white)
        )

        Spacer(modifier = Modifier.size(28.dp))

        AuthField(
            title = stringResource(R.string.email),
            value = state.email,
            onValueChange = { onEvent(AuthEvent.EmailChanged(it)) },
            placeholder = stringResource(id = R.string.email),
            label = stringResource(id = R.string.label_email),
        )

        Spacer(modifier = Modifier.size(16.dp))

        AuthField(
            title = stringResource(R.string.password),
            value = state.password,
            onValueChange = { onEvent(AuthEvent.PasswordChanged(it)) },
            placeholder = stringResource(id = R.string.password),
            label = stringResource(id = R.string.label_password),
        )

        Spacer(modifier = Modifier.size(16.dp))

        AuthField(
            title = stringResource(R.string.passwor_password),
            value = state.confirmPassword,
            onValueChange = { onEvent(AuthEvent.ConfirmPasswordChanged(it)) },
            placeholder = stringResource(id = R.string.label_password_password),
            label = stringResource(id = R.string.label_password_password),
        )

        Spacer(modifier = Modifier.size(24.dp))

        Button(
            onClick = onRegisterClick,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(30.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = coreR.color.green),
                contentColor = colorResource(id = coreR.color.white),
            )
        ) {
            Text(
                text = stringResource(id = R.string.register),
                fontSize = 14.sp,
            )
        }

        state.errorMessage?.let { message ->
            Text(
                text = message,
                color = MaterialTheme.colorScheme.error,
                fontSize = 13.sp,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(vertical = 8.dp)
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.exist_account),
                fontSize = 12.sp,
                color = colorResource(id = coreR.color.white)
            )
            TextButton(onClick = onLoginClick) {
                Text(
                    text = stringResource(id = R.string.enter),
                    fontSize = 12.sp,
                    color = colorResource(id = coreR.color.green)
                )
            }
        }

        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp),
            color = colorResource(id = coreR.color.white).copy(alpha = 0.2f)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Button(
                onClick = {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://vk.com/"))
                    context.startActivity(intent)
                },
                shape = RoundedCornerShape(30.dp),
                modifier = Modifier.size(156.dp, 40.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = coreR.color.blue),
                    contentColor = colorResource(id = coreR.color.white),
                )
            ) {
                Icon(
                    painter = painterResource(id = coreR.drawable.ic_vk),
                    contentDescription = stringResource(id = R.string.content_description_open_vk),
                    modifier = Modifier.size(50.dp, 43.dp),
                    tint = Color.Unspecified,
                )
            }

            val gradient = Brush.horizontalGradient(
                colors = listOf(Color(0xFFF98509), Color(0xFFF95D00))
            )

            Box(
                modifier = Modifier
                    .size(156.dp, 40.dp)
                    .clip(RoundedCornerShape(30.dp))
                    .background(brush = gradient)
            ) {
                Button(
                    onClick = {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://ok.ru/"))
                        context.startActivity(intent)
                    },
                    shape = RoundedCornerShape(30.dp),
                    modifier = Modifier.fillMaxSize(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                        contentColor = colorResource(id = coreR.color.white),
                    ),
                    contentPadding = ButtonDefaults.ContentPadding
                ) {
                    Icon(
                        painter = painterResource(id = coreR.drawable.ic_ok),
                        contentDescription = stringResource(id = R.string.content_description_open_ok),
                        modifier = Modifier.size(50.dp, 43.dp),
                        tint = Color.Unspecified,
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true, backgroundColor = 0xFF151515)
@Composable
fun RegistrationScreenContentPreview() {
    RegistrationScreenContent(
        state = AuthUiState(),
        onEvent = {},
        onLoginClick = {},
        onRegisterClick = {}
    )
}