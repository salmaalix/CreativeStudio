package dk.itu.creativestudio.ui.features.addinspiration

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun AddInspirationScreen(
    onCancel: () -> Unit,
    viewModel: AddInspirationViewModel = viewModel()
) {

    val uiState by viewModel.uiState.collectAsState()

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

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            Button(
                onClick = onCancel,
                modifier = Modifier.weight(1f)
            ) {
                Text("Cancel")
            }

            Button(
                onClick = {
                    viewModel.onSaveClick()
                    onCancel()
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Save")
            }
        }
    }
}