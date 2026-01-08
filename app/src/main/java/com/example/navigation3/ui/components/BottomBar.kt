package com.example.navigation3.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.example.navigation3.navigation.BottomBarItem

@Composable
fun BottomBar(
    backStack: NavBackStack<NavKey>
) {
    val items = listOf(
        BottomBarItem.Email,
        BottomBarItem.Meet
    )

    val currentRoute = backStack.last()

    NavigationBar {
        items.forEach { item ->
            val selected = currentRoute::class == item.route::class

            NavigationBarItem(
                selected = selected,
                onClick = {
                    if (!selected) {
                        backStack.clear()
                        backStack.add(item.route)
                    }
                },
                icon = {
                    Icon(item.icon, contentDescription = item.label)
                },
                label = {
                    Text(item.label)
                }
            )
        }
    }
}
