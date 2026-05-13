package dk.itu.creativestudio.ui.features.edit

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import dk.itu.creativestudio.CreativeStudioApp
import dk.itu.creativestudio.data.model.Inspiration
import dk.itu.creativestudio.data.repository.InspirationRepository
import kotlinx.coroutines.launch

class EditInspirationViewModel(application: Application)
    : AndroidViewModel(application) {

    private val repository = InspirationRepository(
        (application as CreativeStudioApp)
            .database
            .inspirationDao()
    )

    fun updateInspiration(item: Inspiration) {
        viewModelScope.launch {
            repository.updateInspiration(item)
        }
    }
}