package dk.itu.creativestudio.ui.navigation



import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dk.itu.creativestudio.CreativeStudioApp
import dk.itu.creativestudio.ui.features.addinspiration.AddInspirationScreen
import dk.itu.creativestudio.ui.features.addinspiration.AddInspirationViewModel
import dk.itu.creativestudio.ui.features.detail.DetailScreen
import dk.itu.creativestudio.ui.features.detail.DetailViewModel
import dk.itu.creativestudio.ui.features.edit.EditInspirations
import dk.itu.creativestudio.ui.features.edit.EditInspirationViewModel
import dk.itu.creativestudio.ui.features.inspirationlist.InspirationListScreen
import dk.itu.creativestudio.ui.features.inspirationlist.InspirationListViewModel
import androidx.compose.ui.platform.LocalContext

@Composable
fun AppNavigation() {
    val context = LocalContext.current
    val repository = (context.applicationContext as CreativeStudioApp).repository
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "list"
    ) {
        composable("list") {
            val viewModel: InspirationListViewModel = viewModel(
                factory = InspirationListViewModel.Factory(repository)
            )
            InspirationListScreen(
                viewModel = viewModel,
                onAddClick = {
                    navController.navigate("add") {
                        launchSingleTop = true
                    }
                },
                onItemClick = { navController.navigate("detail/$it") }
            )
        }

        composable("add") {
            val viewModel: AddInspirationViewModel = viewModel(
                factory = AddInspirationViewModel.Factory(repository)
            )
            AddInspirationScreen(
                viewModel = viewModel,
                onCancel = { navController.popBackStack() }
            )
        }

        composable("detail/{id}") { backStackEntry ->
            val id = backStackEntry.arguments
                ?.getString("id")?.toIntOrNull() ?: return@composable
            val viewModel: DetailViewModel = viewModel(
                factory = DetailViewModel.Factory(repository)
            )
            LaunchedEffect(id) { viewModel.loadInspiration(id) }
            DetailScreen(
                viewModel = viewModel,
                onBack = { navController.popBackStack() },
                onEdit = { navController.navigate("edit/$id") },
                onNavigateToList = { navController.popBackStack("list", false) }
            )
        }

        composable("edit/{id}") { backStackEntry ->
            val id = backStackEntry.arguments
                ?.getString("id")?.toIntOrNull() ?: return@composable
            val viewModel: EditInspirationViewModel = viewModel(
                factory = EditInspirationViewModel.Factory(repository)
            )
            LaunchedEffect(id) { viewModel.loadInspiration(id) }
            EditInspirations(
                viewModel = viewModel,
                onCancel = { navController.popBackStack() },
                onSaved = { navController.popBackStack("list", false) }
            )
        }
    }
}