package com.example.geeksrun.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RegistrationScreen() {
    var name by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Добро пожаловать в GEEKS GAME", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.Yellow)

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Имя", color = Color.Yellow) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Yellow,
                unfocusedBorderColor = Color.Yellow,
                cursorColor = Color.Yellow,
                focusedLabelColor = Color.Yellow,
                unfocusedLabelColor = Color.Yellow,
                focusedTextColor = Color.Yellow,
                unfocusedTextColor = Color.Yellow
            )
        )


        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = phone,
            onValueChange = { phone = it },
            label = { Text("Номер телефона", color = Color.Yellow) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Yellow,
                unfocusedBorderColor = Color.Yellow,
                cursorColor = Color.Yellow,
                focusedLabelColor = Color.Yellow,
                unfocusedLabelColor = Color.Yellow,
                focusedTextColor = Color.Yellow,
            )
        )


        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                Log.d("Registration", "Имя: $name, Телефон: $phone")
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Yellow),
            modifier = Modifier.fillMaxWidth()
                .height(50.dp)
        ) {
            Text("Начать игру", color = Color.Black)
        }
    }
}
