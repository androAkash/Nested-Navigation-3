package com.example.navigation3.navigation

import androidx.navigation3.runtime.NavKey
import com.example.navigation3.R
import kotlinx.serialization.Serializable

@Serializable
data class AuthenticationScreen(val refreshTrigger: Int = 0) : NavKey

@Serializable
data class BottomNavigationScreen(val refreshTrigger: Int = 0) : NavKey

// Home Tab Screens
@Serializable
data class HomeScreen(val refreshTrigger: Int = 0) : NavKey

@Serializable
data class HomeDetailScreen(val itemId: String) : NavKey

// Detail Tab Screens
@Serializable
data class DetailScreen(val refreshTrigger: Int = 0) : NavKey

@Serializable
data class DetailSubScreen(val detailId: String) : NavKey

interface BottomNavItem {
    val fillIcon: Int
    val outlineIcon: Int
    val title: String
    val rootScreen: NavKey
}

enum class BottomNavTab(
    override val fillIcon: Int,
    override val outlineIcon: Int,
    override val title: String,
    override val rootScreen: NavKey
) : BottomNavItem {
    HOME(
        fillIcon = R.drawable.baseline_home_24,
        outlineIcon = R.drawable.outline_home_24,
        title = "Home",
        rootScreen = HomeScreen()
    ),
    DETAIL(
        fillIcon = R.drawable.baseline_person_24,
        outlineIcon = R.drawable.outline_person_24,
        title = "Detail",
        rootScreen = DetailScreen()
    )
}
