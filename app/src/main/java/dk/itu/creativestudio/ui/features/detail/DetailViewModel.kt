package dk.itu.creativestudio.ui.features.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import dk.itu.creativestudio.data.model.Inspiration
import dk.itu.creativestudio.data.repository.InspirationRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repository: InspirationRepository
) : ViewModel() {

    sealed interface NavigationEvent {
        data class OpenUrl(val url: String) : NavigationEvent
    }

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    private val _events = MutableSharedFlow<NavigationEvent>()
    val events = _events.asSharedFlow()

    fun loadInspiration(id: Int) {
        viewModelScope.launch {
            val item = repository.getById(id) ?: return@launch
            _uiState.update { it.copy(inspiration = item) }
        }
    }

    fun onVideoClick() {
        val url = _uiState.value.inspiration?.videoUrl ?: return
        if (url.isBlank()) return
        viewModelScope.launch {
            _events.emit(NavigationEvent.OpenUrl(url))
        }
    }

    fun onDeleteClick() {
        viewModelScope.launch {
            uiState.value.inspiration?.let {
                repository.deleteInspiration(it)
                _uiState.update { s -> s.copy(isDeleted = true) }
            }
        }
    }

    data class UiState(
        val inspiration: Inspiration? = null,
        val isDeleted: Boolean = false
    )

    class Factory(private val repository: InspirationRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            @Suppress("UNCHECKED_CAST")
            return DetailViewModel(repository) as T
        }
    }
}