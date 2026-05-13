package dk.itu.creativestudio.ui.features.inspirationlist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import dk.itu.creativestudio.CreativeStudioApp
import dk.itu.creativestudio.data.model.Inspiration
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import dk.itu.creativestudio.data.repository.InspirationRepository

class InspirationListViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = InspirationRepository(
        (application as CreativeStudioApp)
            .database
            .inspirationDao()
    )

    val inspirations: StateFlow<List<Inspiration>> = repository.getAllInspirations().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptyList()
    )

    init {
        viewModelScope.launch {

            val count = repository.getCount()

            if (count == 0) {

                repository.insertInspiration(
                    Inspiration(
                        title = "Crochet laptop sleeve",
                        notes = """
                        Needle size: 15 mm
                        Yarn: T-shirt yarn 
                        Color: Red
                        Add ons: Ribbons
                    """.trimIndent(),
                        imageUrl = "https://i.pinimg.com/originals/eb/4c/61/eb4c61cbf3d3db17e3844c4698198f5d.png",
                        videoUrl = "https://www.youtube.com/shorts/opwa2DrHAhg"
                    )
                )

                repository.insertInspiration(
                    Inspiration(
                        title = "Matcha bowl and whisk holder",
                        notes = """
                        Inspo: Studio Ghibli
                        Add: Ponyo figures on the bowl and optionally hearts in between
                        Colors: pastel - yellow and pink
                    """.trimIndent(),
                        imageUrl = "https://i.pinimg.com/originals/6e/2a/80/6e2a804cc09a8e22fff76bdaccc63da3.png",
                        videoUrl = ""
                    )
                )
            }
        }
    }
}