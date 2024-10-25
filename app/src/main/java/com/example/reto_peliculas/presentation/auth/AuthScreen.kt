package com.example.reto_peliculas.presentation.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.reto_peliculas.R
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

@Composable
fun AuthScreen(
    state: AuthState,
    screenHandler: (AuthAction) -> Unit,
    navigate: () -> Unit,
    events: SharedFlow<AuthEvent>,
) {
    LaunchedEffect(key1 = Unit) {
        events.collect { event ->
            when (event) {
                AuthEvent.NavigateToHome -> navigate()
            }

        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
        ) {
            Text(
                text = "Ingresar",
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(bottom = 24.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = ""
            )
            OutlinedTextField(
                value = state.user,
                onValueChange = { screenHandler(AuthAction.HandleUserChange(it)) },
                label = { Text("Email") },
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )
            OutlinedTextField(
                value = state.password,
                onValueChange = { screenHandler(AuthAction.HandlePasswordChange(it)) },
                label = { Text("Password") },
                shape = RoundedCornerShape(12.dp),
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )
            Spacer(Modifier.height(20.dp))
            Text(
                text = "¿Olvidaste tu contraseña?",
                color = Color.Black
            )
            Spacer(Modifier.height(20.dp))
            Button(
                onClick = { screenHandler(AuthAction.ValidateCredential) },
                modifier = Modifier.fillMaxWidth(),
                enabled = state.enabled,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6440FE))
            ) {
                Text(text = "Login")
            }
            Spacer(modifier = Modifier.weight(1f))

        }
    }
}

@Preview(showBackground = true)
@Composable
fun AuthScreenPreview() {
    AuthScreen(
        state = AuthState.buildInitialState(),
        screenHandler = {},
        navigate = {},
        events = MutableSharedFlow(),
    )
}