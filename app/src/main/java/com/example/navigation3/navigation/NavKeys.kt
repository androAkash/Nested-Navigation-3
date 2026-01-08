package com.example.navigation3.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.outlined.Videocam
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
data object EmailScreen : NavKey
@Serializable
data object MeetScreen : NavKey
@Serializable
data object ProfileScreen : NavKey

sealed class BottomBarItem(
    val route: NavKey,
    val label: String,
    val icon: ImageVector
) {
    data object Email : BottomBarItem(
        route = EmailScreen,
        label = "Email",
        icon = Icons.Default.Email
    )

    data object Meet : BottomBarItem(
        route = MeetScreen,
        label = "Meet",
        icon = Icons.Outlined.Videocam
    )
}
