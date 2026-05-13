package dk.itu.creativestudio.ui.features.addinspiration

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import dk.itu.creativestudio.CreativeStudioApp
import dk.itu.creativestudio.data.model.Inspiration
import dk.itu.creativestudio.data.repository.InspirationRepository
import kotlinx.coroutines.launch

class AddInspirationViewModel(application: Application)
    : AndroidViewModel(application) {

    private val repository = InspirationRepository(
        (application as CreativeStudioApp)
            .database
            .inspirationDao()
    )

    fun addInspiration(
        title: String,
        notes: String,
        imageUrl: String,
        videoUrl: String
    ) {

        viewModelScope.launch {

            repository.insertInspiration(
                Inspiration(
                    title = title,
                    notes = notes,
                    imageUrl = imageUrl,
                    videoUrl = videoUrl
                )
            )
        }
    }
}