package dk.itu.creativestudio

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dk.itu.creativestudio.ui.theme.CreativeStudioTheme
import androidx.navigation.compose.*
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CreativeStudioTheme {

                val viewModel: InspirationViewModel = viewModel()
                val navController = rememberNavController()

                NavHost(navController, startDestination = "list") {


                    composable("list") {
                        InspirationListScreen(
                            viewModel,
                            onAddClick = { navController.navigate("add") },
                            onItemClick = {
                                navController.navigate("detail/${it.id}")
                            }
                        )
                    }

                    composable("add") {
                        AddInspirationScreen(
                            onSave = { title, notes, imageUrl, videoUrl ->
                                viewModel.addInspiration(title, notes, imageUrl, videoUrl)
                                navController.popBackStack("list", false)
                            },
                            onCancel = {
                                navController.popBackStack()
                            }
                        )
                    }

                    composable("detail/{id}") { backStackEntry ->

                        val id = backStackEntry.arguments?.getString("id")?.toIntOrNull()
                        val item = viewModel.inspirations.value.find { it.id == id }

                        if (item != null) {
                            DetailScreen(
                                inspiration = item,
                                onBack = { navController.popBackStack() },
                                onEdit = {
                                    navController.navigate("edit/${item.id}")
                                },
                                onDelete = {
                                    viewModel.deleteInspiration(it)
                                    navController.popBackStack("list", false)
                                }
                            )
                        }
                    }

                    composable("edit/{id}") { backStackEntry ->

                        val id = backStackEntry.arguments?.getString("id")?.toIntOrNull()
                        val item = viewModel.inspirations.value.find { it.id == id }

                        if (item != null) {
                            EditInspirations(
                                inspiration = item,
                                onSave = { title, notes, imageUrl, videoUrl ->

                                    viewModel.updateInspiration(
                                        item.copy(
                                            title = title,
                                            notes = notes,
                                            imageUrl = imageUrl,
                                            videoUrl = videoUrl
                                        )
                                    )

                                    navController.popBackStack("list", false)
                                },
                                onCancel = {
                                    navController.popBackStack()
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}