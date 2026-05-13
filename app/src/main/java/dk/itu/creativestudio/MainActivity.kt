package dk.itu.creativestudio

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dk.itu.creativestudio.ui.features.addinspiration.AddInspirationScreen
import dk.itu.creativestudio.ui.features.addinspiration.AddInspirationViewModel
import dk.itu.creativestudio.ui.features.detail.DetailScreen
import dk.itu.creativestudio.ui.features.detail.DetailViewModel
import dk.itu.creativestudio.ui.features.edit.EditInspirations
import dk.itu.creativestudio.ui.features.edit.EditInspirationViewModel
import dk.itu.creativestudio.ui.features.inspirationlist.InspirationListScreen
import dk.itu.creativestudio.ui.features.inspirationlist.InspirationListViewModel
import dk.itu.creativestudio.ui.theme.CreativeStudioTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            CreativeStudioTheme {

                val listViewModel: InspirationListViewModel = viewModel()
                val addViewModel: AddInspirationViewModel = viewModel()
                val editViewModel: EditInspirationViewModel = viewModel()
                val detailViewModel: DetailViewModel = viewModel()

                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "list"
                ) {

                    composable("list") {

                        InspirationListScreen(
                            listViewModel,
                            onAddClick = {
                                navController.navigate("add")
                            },
                            onItemClick = {
                                navController.navigate("detail/${it.id}")
                            }
                        )
                    }

                    composable("add") {

                        AddInspirationScreen(
                            onSave = { title, notes, imageUrl, videoUrl ->

                                addViewModel.addInspiration(
                                    title,
                                    notes,
                                    imageUrl,
                                    videoUrl
                                )

                                navController.popBackStack("list", false)
                            },
                            onCancel = {
                                navController.popBackStack()
                            }
                        )
                    }

                    composable("detail/{id}") { backStackEntry ->

                        val id = backStackEntry.arguments
                            ?.getString("id")
                            ?.toIntOrNull()

                        val item = listViewModel
                            .inspirations
                            .value
                            .find { it.id == id }

                        if (item != null) {

                            DetailScreen(
                                inspiration = item,

                                onBack = {
                                    navController.popBackStack()
                                },

                                onEdit = {
                                    navController.navigate("edit/${item.id}")
                                },

                                onDelete = {

                                    detailViewModel.deleteInspiration(item)

                                    navController.popBackStack("list", false)
                                }
                            )
                        }
                    }

                    composable("edit/{id}") { backStackEntry ->

                        val id = backStackEntry.arguments
                            ?.getString("id")
                            ?.toIntOrNull()

                        val item = listViewModel
                            .inspirations
                            .value
                            .find { it.id == id }

                        if (item != null) {

                            EditInspirations(
                                inspiration = item,

                                onSave = { title, notes, imageUrl, videoUrl ->

                                    editViewModel.updateInspiration(
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