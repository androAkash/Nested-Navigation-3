package com.example.navigation3.navigation

import androidx.navigation3.runtime.NavKey
import com.example.navigation3.R
import kotlinx.serialization.Serializable

@Serializable
data class BottomNavigationScreen(
    val refreshTrigger: Int = 0,
) : NavKey

@Serializable
data class HomeScreen(
    val refreshTrigger: Int = 0,
) : NavKey, BottomNavItem {
    override val fillIcon: Int = R.drawable.baseline_home_24
    override val outlineIcon: Int = R.drawable.outline_home_24
    override val title: String = "Home"
}

@Serializable
data class DetailScreen(
    val cityId: Int = 0
) : NavKey, BottomNavItem {
    override val fillIcon: Int = R.drawable.baseline_person_24
    override val outlineIcon: Int = R.drawable.outline_person_24
    override val title: String = "Detail"
}

interface BottomNavItem {
    val fillIcon: Int
    val outlineIcon: Int
    val title: String
}