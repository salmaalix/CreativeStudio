package dk.itu.creativestudio

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class InspirationViewModel(application: Application) : AndroidViewModel(application) {

    private val dao =
        (application as CreativeStudioApp).database.inspirationDao()

    val inspirations: StateFlow<List<Inspiration>> = dao.getAll().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptyList()
    )

    init {
        viewModelScope.launch {

            val count = dao.getCount()

            if (count == 0) {

                dao.insert(
                    Inspiration(
                        title = "Crochet laptop sleeve",
                        notes = """
                        Needle size: 15 mm
                        Yarn: T-shirt yarn 
                        Color: Red
                        Add ons: Ribbons
                    """.trimIndent(),
                        imageUrl = "https://loosendscrochet.com/cdn/shop/files/DSC01306.jpg?v=1772127517&width=3000",
                        videoUrl = "https://www.youtube.com/shorts/opwa2DrHAhg"
                    )
                )

                dao.insert(
                    Inspiration(
                        title = "Matcha bowl and whisk holder",
                        notes = """
                        Inspo: Studio Ghibli
                        Add: Calcifer, Ponyo and Totoro figures on the bowl
                        Colors: pastel - yellow and pink
                    """.trimIndent(),
                        imageUrl = "https://i.etsystatic.com/63666736/r/il/197a1f/7978779841/il_1588xN.7978779841_j254.jpg",
                        videoUrl = ""
                    )
                )
            }
        }
    }

    fun addInspiration(
        title: String,
        notes: String,
        imageUrl: String,
        videoUrl: String
    ) {
        viewModelScope.launch {
            dao.insert(
                Inspiration(
                    title = title,
                    notes = notes,
                    imageUrl = imageUrl,
                    videoUrl = videoUrl
                )
            )
        }
    }

    fun updateInspiration(item: Inspiration) {
        viewModelScope.launch {
            dao.update(item)
        }
    }

    fun deleteInspiration(item: Inspiration) {
        viewModelScope.launch {
            dao.delete(item)
        }
    }
}