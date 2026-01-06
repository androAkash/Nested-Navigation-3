package com.example.navigation3.navigation

import androidx.navigation3.runtime.NavKey
import com.example.navigation3.R
import kotlinx.serialization.Serializable

sealed interface RootNav : NavKey
sealed interface BottomNav : NavKey

@Serializable
data class AuthenticationScreen(
    val refreshTrigger: Int = 0
) :RootNav

@Serializable
data class BottomNavigationScreen(
    val refreshTrigger: Int = 0
) :RootNav

@Serializable
data class HomeScreen(
    val refreshTrigger: Int = 0
) :NavKey, BottomNav

@Serializable
data class ProfileScreen(
    val refreshTrigger: Int = 0
) :BottomNav

@Serializable
data class DetailScreen(
    val title: Int,
    val refreshTrigger: Int = 0
) : RootNav

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
    PROFILE(
        fillIcon = R.drawable.baseline_person_24,
        outlineIcon = R.drawable.outline_person_24,
        title = "Profile",
        rootScreen = ProfileScreen()
    )
}
