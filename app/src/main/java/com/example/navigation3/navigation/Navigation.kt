package com.example.navigation3.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.navigation3.ui.screens.BottomNavScreen
import com.example.navigation3.ui.screens.AuthScreenUi

@Composable
fun Navigation() {
    val backStack = rememberNavBackStack(AuthenticationScreen())

    NavDisplay(
        modifier = Modifier.fillMaxSize(),
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = { key ->
            when (key) {
                is AuthenticationScreen -> NavEntry(key) {
                    AuthScreenUi(
                        modifier = Modifier.fillMaxSize(),
                        onNavigate = {
                            backStack.clear()
                            backStack.add(BottomNavigationScreen())
                        }
                    )
                }

                is BottomNavigationScreen -> NavEntry(key) {
                    BottomNavScreen(
                        modifier = Modifier.fillMaxSize(),
                        onNavigate = {
                            backStack.clear()
                            backStack.add(AuthenticationScreen())
                        }
                    )
                }

                else -> throw RuntimeException("Invalid NavKey for root navigation.")
            }
        },
        transitionSpec = {
            slideInVertically(
                initialOffsetY = { it },
                animationSpec = tween(1000)
            ) togetherWith ExitTransition.KeepUntilTransitionsFinished
        },
        popTransitionSpec = {
            EnterTransition.None togetherWith slideOutVertically(
                targetOffsetY = { it },
                animationSpec = tween(1000)
            )
        }
    )
}