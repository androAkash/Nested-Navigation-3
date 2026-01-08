package com.example.navigation3.navigation

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.navigation3.ui.components.BottomBar
import com.example.navigation3.ui.screens.EmailScreenUi
import com.example.navigation3.ui.screens.MeetScreenUi
import com.example.navigation3.ui.screens.ProfileScreenUi
import kotlinx.coroutines.launch

private val bottomBarRoots = setOf(
    EmailScreen::class,
    MeetScreen::class
)
@Composable
fun Navigation() {
    val backStack = rememberNavBackStack(EmailScreen)
    val currentRoute = backStack.last()
    val showBottomBar = currentRoute::class in bottomBarRoots
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
        ModalDrawerSheet(drawerShape = RoundedCornerShape(0.dp)) {
            Text("Gmail", modifier = Modifier.padding(16.dp))
            HorizontalDivider()
            NavigationDrawerItem(
                label = { Text(text = "All inboxes") },
                selected = false,
                onClick = { /*TODO*/ }
            )
        }
    }) {
        Scaffold(
            bottomBar = {
                if (showBottomBar) {
                    BottomBar(backStack = backStack)
                }
            }
        ) { padding ->
            NavDisplay(backStack = backStack,
                onBack = {},
                entryProvider = { key ->
                    when(key){
                        is EmailScreen -> NavEntry(key){
                            EmailScreenUi(
                                backStack = backStack,
                                onDrawer = {
                                    scope.launch { drawerState.open() }
                                }
                            )
                        }
                        is MeetScreen -> NavEntry(key){
                            MeetScreenUi()
                        }
                        is ProfileScreen -> NavEntry(key){
                            ProfileScreenUi(
                                backStack = backStack
                            )
                        }
                        else -> error("Unknown NavKey")
                    }
                },
                transitionSpec = {
                    fadeIn() + slideInHorizontally { it / 4 } togetherWith
                            fadeOut() + slideOutHorizontally { -it / 4 }
                }
            )
        }
    }
}