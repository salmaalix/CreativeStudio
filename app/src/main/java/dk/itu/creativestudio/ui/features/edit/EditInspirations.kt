package dk.itu.creativestudio.ui.features.edit

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dk.itu.creativestudio.data.model.Inspiration

@Composable
fun EditInspirations(
    inspiration: Inspiration,
    onSave: (String, String, String, String) -> Unit,
    onCancel: () -> Unit
) {

    var title by remember { mutableStateOf(inspiration.title) }
    var notes by remember { mutableStateOf(inspiration.notes) }
    var imageUrl by remember { mutableStateOf(inspiration.imageUrl) }
    var videoUrl by remember { mutableStateOf(inspiration.videoUrl) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Title") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = notes,
            onValueChange = { notes = it },
            label = { Text("Notes") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = imageUrl,
            onValueChange = { imageUrl = it },
            label = { Text("Image URL") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = videoUrl,
            onValueChange = { videoUrl = it },
            label = { Text("Video URL") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            onSave(title, notes, imageUrl, videoUrl)
        }) {
            Text("Save Changes")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = onCancel) {
            Text("Cancel")
        }
    }
}