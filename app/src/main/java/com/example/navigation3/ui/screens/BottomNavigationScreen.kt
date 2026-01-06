package com.example.navigation3.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.navigation3.navigation.BottomNavTab
import com.example.navigation3.navigation.DetailScreen
import com.example.navigation3.navigation.ProfileScreen
import com.example.navigation3.navigation.HomeScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BottomNavScreen(modifier: Modifier = Modifier,onOpenDetail: (Int) -> Unit) {

    val bottomNavItems = remember { BottomNavTab.entries }
    val homeBackStack = rememberNavBackStack(HomeScreen())
    val detailBackStack = rememberNavBackStack(ProfileScreen())
    var selectedTabIndex by rememberSaveable { mutableIntStateOf(0) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar(
                containerColor = Color.LightGray,
                tonalElevation = 8.dp
            ) {
                bottomNavItems.forEachIndexed { index, item ->
                    val selected = selectedTabIndex == index
                    val isHomeSelected = selected && index == 0

                    val hapticFeedback = LocalHapticFeedback.current

                    NavigationBarItem(
                        modifier = Modifier.background(color = Color.LightGray),
                        selected = selected,
                        onClick = {
                            hapticFeedback.performHapticFeedback(HapticFeedbackType.LongPress)

                            if (isHomeSelected) {
                                homeBackStack.clear()
                                homeBackStack.add(HomeScreen())
                            } else if (selected) {
                                when (index) {
                                    0 -> {
                                        homeBackStack.clear()
                                        homeBackStack.add(HomeScreen())
                                    }
                                    1 -> {
                                        detailBackStack.clear()
                                        detailBackStack.add(ProfileScreen())
                                    }
                                }
                            } else {
                                // Switch tab
                                selectedTabIndex = index
                            }
                        },
                        icon = {
                            Icon(
                                painter = if (selected)
                                    painterResource(item.fillIcon)
                                else
                                    painterResource(item.outlineIcon),
                                contentDescription = item.title,
                                tint = Color.Black,
                                modifier = Modifier.size(24.dp)
                            )
                        },
                        label = {
                            Text(
                                text = item.title,
                                color = Color.Black,
                                fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color.Transparent,
                            selectedTextColor = Color.Transparent,
                            indicatorColor = Color.Transparent,
                            unselectedIconColor = Color.Transparent,
                            unselectedTextColor = Color.Transparent
                        )
                    )
                }
            }
        }
    ) { innerPadding ->
        val screenModifier = modifier
            .fillMaxSize()
            .padding(innerPadding)
            when (selectedTabIndex) {
                0 -> {
                    NavDisplay(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = Color.Gray),
                        backStack = homeBackStack,
                        onBack = { homeBackStack.removeLastOrNull() },
                        entryProvider = { key ->
                            when (key) {
                                is HomeScreen -> {
                                    NavEntry(key = key) {
                                        HomeScreenUi(
                                            modifier = screenModifier,
                                            data = "",
                                            onItemClick = { title ->
                                                onOpenDetail(title)
                                            }
                                        )
                                    }
                                }
                                else -> throw RuntimeException("Invalid NavKey for Home tab.")
                            }
                        }
                    )
                }

                1 -> {
                    NavDisplay(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = Color.White),
                        backStack = detailBackStack,
                        onBack = { detailBackStack.removeLastOrNull() },
                        entryProvider = { key ->
                            when (key) {
                                is ProfileScreen -> {
                                    NavEntry(key = key) {
                                        ProfileScreenUi(
                                            modifier = screenModifier,
                                            onLogout = {
                                                homeBackStack.clear()
                                                homeBackStack.add(HomeScreen())
                                                selectedTabIndex = 0
                                            }
                                        )
                                    }
                                }
                                else -> throw RuntimeException("Invalid NavKey for Profile tab.")
                            }
                        }
                    )
                }
            }

    }
}