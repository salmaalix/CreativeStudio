package dk.itu.creativestudio.ui.features.edit

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import dk.itu.creativestudio.data.model.Inspiration
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

@Composable
fun EditInspirations(
    inspiration: Inspiration,
    onCancel: () -> Unit,
    onSaved: () -> Unit,
    viewModel: EditInspirationViewModel = viewModel()
) {

    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(inspiration.id) {
        viewModel.setInspiration(inspiration)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        OutlinedTextField(
            value = uiState.title,
            onValueChange = viewModel::onTitleChange,
            label = { Text("Title") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = uiState.notes,
            onValueChange = viewModel::onNotesChange,
            label = { Text("Notes") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = uiState.imageUrl,
            onValueChange = viewModel::onImageUrlChange,
            label = { Text("Image URL") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = uiState.videoUrl,
            onValueChange = viewModel::onVideoUrlChange,
            label = { Text("Video URL") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                MainScope().launch {

                    viewModel.onSaveClick(inspiration.id)

                    onSaved()
                }
            }
        ) {
            Text("Save Changes")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = onCancel) {
            Text("Cancel")
        }
    }
}