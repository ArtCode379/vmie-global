package shop.vmieglobal.app.ui.composable.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import shop.vmieglobal.app.ui.composable.screen.cart.VMGLOCartScreen
import shop.vmieglobal.app.ui.composable.screen.checkout.VMGLOCheckoutScreen
import shop.vmieglobal.app.ui.composable.screen.onboarding.VMGLOOnboardingScreen
import shop.vmieglobal.app.ui.composable.screen.orders.VMGLOOrdersScreen
import shop.vmieglobal.app.ui.composable.screen.productdetails.VMGLOProductDetailsScreen
import shop.vmieglobal.app.ui.composable.screen.products.VMGLOProductsScreen
import shop.vmieglobal.app.ui.composable.screen.settings.VMGLOSettingsScreen
import shop.vmieglobal.app.ui.composable.screen.splash.VMGLOSplashScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = NavRoute.Splash,
        modifier = modifier,
    ) {
        composable<NavRoute.Splash> {
            VMGLOSplashScreen(
                onNavigateToHomeScreen = {
                    navController.navigate(route = NavRoute.Home) {
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                },
                onNavigateToOnboarding = {
                    navController.navigate(route = NavRoute.Onboarding) {
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }
            )
        }

        composable<NavRoute.Onboarding> {
            VMGLOOnboardingScreen(
                onNavigateToHomeScreen = {
                    navController.navigate(NavRoute.Home) {
                        popUpTo(NavRoute.Onboarding) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }
            )
        }

        composable<NavRoute.Home> {
            VMGLOProductsScreen(
                onNavigateToProductDetails = { id: Int ->
                    navController.navigate(
                        route = NavRoute.ProductDetails(id = id)
                    )
                }
            )
        }

        composable<NavRoute.ProductDetails> { backStackEntry ->
            val productDetails: NavRoute.ProductDetails = backStackEntry.toRoute()
            VMGLOProductDetailsScreen(
                productId = productDetails.id,
            )
        }

        composable<NavRoute.Cart> {
            VMGLOCartScreen(
                onNavigateToVMGLOCheckoutScreen = {
                    navController.navigate(NavRoute.Checkout)
                }
            )
        }

        composable<NavRoute.Checkout> {
            VMGLOCheckoutScreen(
                onNavigateToVMGLOOrdersScreen = {
                    navController.navigate(NavRoute.Orders) {
                        popUpTo(NavRoute.Home) { inclusive = false }
                    }
                }
            )
        }

        composable<NavRoute.Orders> {
            VMGLOOrdersScreen()
        }

        composable<NavRoute.Settings> {
            VMGLOSettingsScreen()
        }
    }
}