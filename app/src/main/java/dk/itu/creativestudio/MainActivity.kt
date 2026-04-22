package dk.itu.creativestudio

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dk.itu.creativestudio.ui.theme.CreativeStudioTheme
import androidx.lifecycle.viewmodel.compose.viewModel



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CreativeStudioTheme {
                val viewModel: InspirationViewModel = viewModel()
                InspirationListScreen(viewModel)
            }

        }
    }
}

