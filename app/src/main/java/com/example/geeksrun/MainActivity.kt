package com.example.geeksrun


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import com.example.geeksrun.ui.GameOverScreen
import com.example.geeksrun.ui.GameScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GeeksGameApp()
        }
    }
}

@Composable
fun GeeksGameApp() {
    var currentScreen by remember { mutableStateOf("game") }
    var score by remember { mutableStateOf(0) }

    when (currentScreen) {
        "game" -> GameScreen(onGameOver = {
            score = it
            currentScreen = "gameover"
        })

        "gameover" -> GameOverScreen(score = score) {
            currentScreen = "game"
        }
    }
}
