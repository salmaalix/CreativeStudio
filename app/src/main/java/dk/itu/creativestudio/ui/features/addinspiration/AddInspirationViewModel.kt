package dk.itu.creativestudio.ui.features.addinspiration

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import dk.itu.creativestudio.CreativeStudioApp
import dk.itu.creativestudio.data.model.Inspiration
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AddInspirationViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val repository =
        (application as CreativeStudioApp).repository

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    fun onTitleChange(title: String) {
        _uiState.update {
            it.copy(title = title)
        }
    }

    fun onNotesChange(notes: String) {
        _uiState.update {
            it.copy(notes = notes)
        }
    }

    fun onImageUrlChange(imageUrl: String) {
        _uiState.update {
            it.copy(imageUrl = imageUrl)
        }
    }

    fun onVideoUrlChange(videoUrl: String) {
        _uiState.update {
            it.copy(videoUrl = videoUrl)
        }
    }

    fun onSaveClick() {

        viewModelScope.launch {

            repository.insertInspiration(
                Inspiration(
                    title = uiState.value.title,
                    notes = uiState.value.notes,
                    imageUrl = uiState.value.imageUrl,
                    videoUrl = uiState.value.videoUrl
                )
            )
        }
    }

    data class UiState(
        val title: String = "",
        val notes: String = "",
        val imageUrl: String = "",
        val videoUrl: String = ""
    )
}