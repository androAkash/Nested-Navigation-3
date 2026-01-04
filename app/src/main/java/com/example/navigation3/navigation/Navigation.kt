package com.example.navigation3.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.navigation3.ui.screens.BottomNavScreen

@Composable
fun Navigation() {
    val backStack = rememberNavBackStack(BottomNavigationScreen())

    NavDisplay(
        modifier = Modifier,
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = { key ->
            when (key) {
                is BottomNavigationScreen ->
                    NavEntry(key = key) {
                        BottomNavScreen(modifier = Modifier)
                    }

                else -> throw RuntimeException("Invalid NavKey.")
            }
        }
    )
}