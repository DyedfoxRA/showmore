package com.venture.show

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun MainScreen(onNavigate: () -> Unit) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column {
            Button(onClick = onNavigate) {
                Text("Navigate to Products List")
            }
            Button(onClick = { /* Handle click */ }) {
                Text("Button 2")
            }
            Button(onClick = { /* Handle click */ }) {
                Text("Button 3")
            }
        }
    }
}
