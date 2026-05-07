package dk.itu.creativestudio

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Alignment
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.ui.text.font.FontWeight
import coil.compose.AsyncImage

@Composable
fun InspirationListScreen(
    viewModel: InspirationViewModel,
    onAddClick: () -> Unit,
    onItemClick: (Inspiration) -> Unit
) {

    val inspirationsState = viewModel.inspirations.collectAsState(initial = emptyList())
    val inspirations = inspirationsState.value

    Scaffold(
        containerColor = Color(0xFFF9CE69),
        floatingActionButton = {

            Box(
                modifier = Modifier.size(72.dp),
                contentAlignment = Alignment.Center
            ) {
                IconButton(
                    onClick = onAddClick,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = "Add",
                        tint = Color.Red,
                        modifier = Modifier.fillMaxSize()
                    )
                }

                Text(
                    text = "+",
                    color = Color.Black,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {

            val shrikhandFont = FontFamily(
                Font(R.font.shrikhand_regular)
            )

            val poppinsItalic = FontFamily(
                Font(R.font.poppins_bolditalic)
            )

            Text(
                text = "Creative Studio",
                fontFamily = shrikhandFont,
                color = Color(0xFF3A2E8C),
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontSize = 36.sp
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                textAlign = TextAlign.Center
            )

            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(inspirations) { item: Inspiration ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = { onItemClick(item) },
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFFF9CE69)
                        ),
                        elevation = CardDefaults.cardElevation(0.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {

                            AsyncImage(
                                model = item.imageUrl,
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(150.dp)
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                text = item.title,
                                fontFamily = poppinsItalic,
                                style = MaterialTheme.typography.titleMedium,
                                color = Color(0xFF3A2E8C),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 8.dp),
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
        }
    }
}