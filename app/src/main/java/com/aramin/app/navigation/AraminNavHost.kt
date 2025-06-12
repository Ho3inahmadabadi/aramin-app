package com.aramin.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aramin.app.SoundViewModel
import com.aramin.app.ui.screens.MainScreen
import com.aramin.app.ui.screens.PlayerScreen
import com.aramin.app.ui.screens.WelcomeScreen

@Composable
fun AraminNavHost(soundViewModel: SoundViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.Welcome) {
        composable(Routes.Welcome) {
            WelcomeScreen(onStart = { navController.navigate(Routes.Main) })
        }
        composable(Routes.Main) {
            MainScreen(navController, soundViewModel)
        }
        composable("${Routes.Player}/{soundId}") { backStackEntry ->
            val soundId = backStackEntry.arguments?.getString("soundId")?.toInt() ?: 0
            PlayerScreen(navController, soundId, soundViewModel)
        }
    }
}
