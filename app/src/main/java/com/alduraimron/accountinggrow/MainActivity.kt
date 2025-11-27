package com.alduraimron.accountinggrow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.alduraimron.accountinggrow.ui.screens.AddSavingScreen
import com.alduraimron.accountinggrow.ui.screens.DashboardScreen
import com.alduraimron.accountinggrow.ui.screens.LoginScreen
import com.alduraimron.accountinggrow.ui.screens.OnboardingScreen
import com.alduraimron.accountinggrow.ui.screens.PinScreen
import com.alduraimron.accountinggrow.ui.screens.ReportScreen
import com.alduraimron.accountinggrow.ui.screens.SavingScreen
import com.alduraimron.accountinggrow.ui.screens.SplashScreen
import com.alduraimron.accountinggrow.ui.screens.TransactionScreen
import com.alduraimron.accountinggrow.ui.screens.auth.AuthViewModel
import com.alduraimron.accountinggrow.ui.theme.AccountingGrowTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AccountingGrowTheme {
                Surface (
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation()
                }
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val authViewModel: AuthViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = "splash"
    ) {
        composable("splash") {
            SplashScreen(navController)
        }
        composable("onboarding") {
            OnboardingScreen(navController)
        }
        composable("login") {
            LoginScreen(
                navController = navController,
                authViewModel = authViewModel)
        }
        composable("pin") {
            PinScreen(navController)
        }
        composable("dashboard") {
            DashboardScreen(navController)
        }
        composable("transaction") {
            TransactionScreen(navController)
        }
        composable("report") {
            ReportScreen(navController)
        }
        composable("saving") {
            SavingScreen(navController)
        }
        composable("add_saving") {
            AddSavingScreen(navController)
        }
    }
}
