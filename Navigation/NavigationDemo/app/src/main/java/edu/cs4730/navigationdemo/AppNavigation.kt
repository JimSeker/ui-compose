package edu.cs4730.navigationdemo

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

enum class Screen {
    ONE,
    TWO,
    THREE
}

sealed class NavigationItem(val route: String) {
    object one : NavigationItem(Screen.ONE.name)
    object two : NavigationItem(Screen.TWO.name)
    object three : NavigationItem(Screen.THREE.name)
}

@Composable
fun AppNavHost(
    modifier: Modifier,
    navController: NavHostController,
    startDestination: String = NavigationItem.one.route,

    ) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(NavigationItem.one.route) {
            FirstScreen(navController)
        }
        composable(NavigationItem.two.route) {
            SecondScreen(navController)
        }
        composable(NavigationItem.three.route) {
            ThirdScreen(navController)
        }

    }
}