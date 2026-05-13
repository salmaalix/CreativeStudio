package dk.itu.creativestudio.ui.features.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import dk.itu.creativestudio.CreativeStudioApp
import dk.itu.creativestudio.data.model.Inspiration
import dk.itu.creativestudio.data.repository.InspirationRepository
import kotlinx.coroutines.launch

class DetailViewModel(application: Application)
    : AndroidViewModel(application) {

    private val repository = InspirationRepository(
        (application as CreativeStudioApp)
            .database
            .inspirationDao()
    )

    fun deleteInspiration(item: Inspiration) {
        viewModelScope.launch {
            repository.deleteInspiration(item)
        }
    }
}