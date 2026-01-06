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
import com.example.navigation3.ui.screens.DetailScreenUi

@Composable
fun Navigation() {
    val rootBackStack = rememberNavBackStack(AuthenticationScreen())

    NavDisplay(
        modifier = Modifier.fillMaxSize(),
        backStack = rootBackStack,
        onBack = { rootBackStack.removeLastOrNull() },
        entryProvider = { key ->
            when (key) {
                is AuthenticationScreen -> NavEntry(key) {
                    AuthScreenUi(
                        modifier = Modifier.fillMaxSize(),
                        onNavigate = {
                            rootBackStack.clear()
                            rootBackStack.add(BottomNavigationScreen())
                        }
                    )
                }

                is BottomNavigationScreen -> NavEntry(key) {
                    BottomNavScreen(
                        modifier = Modifier.fillMaxSize(),
                        onOpenDetail = { index ->
                            rootBackStack.add(
                                DetailScreen(index)
                            )
                        }
                    )
                }

                is DetailScreen ->
                    NavEntry(key) {
                        DetailScreenUi(
                            index = key.title
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