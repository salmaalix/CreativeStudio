package dk.itu.creativestudio.ui.features.detail

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import coil.compose.AsyncImage
import androidx.compose.ui.text.style.TextAlign
import dk.itu.creativestudio.data.model.Inspiration
import dk.itu.creativestudio.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    inspiration: Inspiration,
    onBack: () -> Unit,
    onEdit: () -> Unit,
    onDelete: (Inspiration) -> Unit
) {

    val context = LocalContext.current


    val shrikhandFont = FontFamily(
        Font(R.font.shrikhand_regular)
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = inspiration.title,
                        fontFamily = shrikhandFont,
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {


            AsyncImage(
                model = inspiration.imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))


            Text(text = inspiration.notes)

            Spacer(modifier = Modifier.height(16.dp))


            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = onBack,
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Back")
                }

                Button(
                    onClick = onEdit,
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Edit")
                }

                if (inspiration.videoUrl.isNotBlank()) {
                    Button(
                        onClick = {
                            val intent = Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse(inspiration.videoUrl)
                            )
                            context.startActivity(intent)
                        },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Video")
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))


            Button(
                onClick = {
                    onDelete(inspiration)
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.error,
                    contentColor = MaterialTheme.colorScheme.onError
                )
            ) {
                Text("Delete")
            }
        }
    }
}