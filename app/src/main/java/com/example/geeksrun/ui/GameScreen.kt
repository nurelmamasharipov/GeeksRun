package com.example.geeksrun.ui


import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

@Composable
fun GameScreen(onGameOver: (score: Int) -> Unit) {
    val coroutineScope = rememberCoroutineScope()
    val playerY = remember { Animatable(900f) }
    val obstacles = remember { mutableStateListOf<Rect>() }
    var isGameOver by remember { mutableStateOf(false) }
    var score by remember { mutableStateOf(0) }
    val density = LocalDensity.current
    val statusBarPadding = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()
    val statusBarHeightPx = with(density) { statusBarPadding.toPx() }


    val playerWidth = 50f
    val playerHeight = 50f
    val playerX = 100f

    LaunchedEffect(Unit) {
        while (!isGameOver) {
            delay(16L)
            score += 1

            // Обновление препятствий
            obstacles.replaceAll {
                Rect(
                    offset = Offset(it.left - 10f, it.top),
                    size = it.size
                )
            }

            // Удаляем вышедшие за экран
            obstacles.removeAll { it.right < 0 }

            // Проверка на столкновение
            val playerRect = Rect(
                offset = Offset(playerX, playerY.value),
                size = Size(playerWidth, playerHeight)
            )
            if (obstacles.any { it.overlaps(playerRect) }) {
                isGameOver = true
                onGameOver(score)
            }

            // Генерация новых препятствий
            if (Random.nextFloat() < 0.02f) {
                obstacles.add(
                    Rect(
                        offset = Offset(800f, 900f),
                        size = Size(50f, 50f)
                    )
                )
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            // Игрок
            drawRect(
                color = Color.Green,
                topLeft = Offset(playerX, playerY.value),
                size = Size(playerWidth, playerHeight)
            )

            // Препятствия
            obstacles.forEach {
                drawRect(
                    color = Color.Red,
                    topLeft = Offset(it.left, it.top),
                    size = it.size
                )
            }

            // Очки
            drawIntoCanvas {
                it.nativeCanvas.drawText(
                    "Score: $score",
                    50f,
                    statusBarHeightPx + 80f, // чуть ниже статус-бара
                    android.graphics.Paint().apply {
                        color = android.graphics.Color.WHITE
                        textSize = 60f
                    }
                )
            }

        }

        // Прыжок по клику
        Box(
            modifier = Modifier
                .matchParentSize()
                .clickable {
                    coroutineScope.launch {
                        if (playerY.value >= 900f) {
                            playerY.animateTo(playerY.value - 300f, animationSpec = tween(300))
                            playerY.animateTo(900f, animationSpec = tween(500))
                        }
                    }
                }
        )
    }
}
