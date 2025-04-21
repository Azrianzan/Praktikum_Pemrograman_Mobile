package com.example.modul1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.modul1.ui.theme.Modul1Theme
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.ui.res.stringResource
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Modul1Theme {
                DiceRollerApp()
            }
        }
    }
}

@Preview
@Composable
fun DiceRollerApp() {
    DiceWithButtonAndImage(modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center))
}

@Composable
fun DiceWithButtonAndImage(modifier: Modifier = Modifier) {
    var result1 by remember { mutableStateOf(0) }
    var result2 by remember { mutableStateOf(0) }
    var message by remember { mutableStateOf("") }

    val scope = rememberCoroutineScope()
    val snackbarHostState = remember {SnackbarHostState()}

    fun getDiceImage(result: Int): Int {
        return when (result) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            6 -> R.drawable.dice_6
            else -> R.drawable.dice_0
        }
    }
    Scaffold (
        snackbarHost = {SnackbarHost(hostState = snackbarHostState)}
    ) { paddingValues ->
        Column (
            modifier = modifier.padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row {
                Image(
                    painter = painterResource(getDiceImage(result1)),
                    contentDescription = result1.toString()
                )
                Image(
                    painter = painterResource(getDiceImage(result2)),
                    contentDescription = result2.toString()
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                result1 = (1..6).random()
                result2 = (1..6).random()
                scope.launch {
                    message = if (result1 == result2 ) "Selamat, anda dapat dadu double!" else "Anda belum beruntung!"
                    snackbarHostState.showSnackbar(
                        message,
                        duration = SnackbarDuration.Short
                    )
                }
            }) {
                Text(stringResource(R.string.roll))
            }
        }
    }
}
