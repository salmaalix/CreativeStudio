package dk.itu.creativestudio.ui.features.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import dk.itu.creativestudio.data.model.Inspiration
import dk.itu.creativestudio.data.repository.InspirationRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class EditInspirationViewModel(
    private val repository: InspirationRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    fun loadInspiration(id: Int) {
        viewModelScope.launch {
            val item = repository.getById(id) ?: return@launch
            _uiState.update {
                it.copy(
                    id = item.id,
                    title = item.title,
                    notes = item.notes,
                    imageUrl = item.imageUrl ?: "",
                    videoUrl = item.videoUrl ?: ""
                )
            }
        }
    }

    fun onTitleChange(title: String) = _uiState.update { it.copy(title = title) }
    fun onNotesChange(notes: String) = _uiState.update { it.copy(notes = notes) }
    fun onImageUrlChange(imageUrl: String) = _uiState.update { it.copy(imageUrl = imageUrl) }
    fun onVideoUrlChange(videoUrl: String) = _uiState.update { it.copy(videoUrl = videoUrl) }

    fun onSaveClick() {
        viewModelScope.launch {
            repository.updateInspiration(
                Inspiration(
                    id = uiState.value.id,
                    title = uiState.value.title.trim(),
                    notes = uiState.value.notes.trim(),
                    imageUrl = uiState.value.imageUrl.trim(),
                    videoUrl = uiState.value.videoUrl.trim()
                )
            )
            _uiState.update { it.copy(isSaved = true) }
        }
    }

    data class UiState(
        val id: Int = 0,
        val title: String = "",
        val notes: String = "",
        val imageUrl: String = "",
        val videoUrl: String = "",
        val isSaved: Boolean = false
    )

    class Factory(private val repository: InspirationRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            @Suppress("UNCHECKED_CAST")
            return EditInspirationViewModel(repository) as T
        }
    }
}