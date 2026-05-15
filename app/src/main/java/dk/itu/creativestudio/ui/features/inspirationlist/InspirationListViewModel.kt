package dk.itu.creativestudio.ui.features.inspirationlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import dk.itu.creativestudio.data.model.Inspiration
import dk.itu.creativestudio.data.repository.InspirationRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class InspirationListViewModel(
    private val repository: InspirationRepository
) : ViewModel() {

    data class UiState(
        val inspirations: List<Inspiration> = emptyList()
    )

    interface UiEvents {
        fun onDeleteInspiration(inspiration: Inspiration)
    }

    val uiState: StateFlow<UiState> = repository.getAllInspirations()
        .map { list -> UiState(inspirations = list) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = UiState()
        )

    fun onDeleteInspiration(inspiration: Inspiration) {
        viewModelScope.launch {
            repository.deleteInspiration(inspiration)
        }
    }

    class Factory(private val repository: InspirationRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            @Suppress("UNCHECKED_CAST")
            return InspirationListViewModel(repository) as T
        }
    }
}