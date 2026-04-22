package dk.itu.creativestudio

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


    class InspirationViewModel: ViewModel() {

        private val _inspirations = MutableStateFlow(
            listOf(
                Inspiration(1, "Clay bowl", "Nice texture", "", ""),
                Inspiration(2, "Silver ring", "Minimal style", "", ""),
                Inspiration(3, "Mug glaze", "Blue tones", "", "")
            )
        )

        val inspirations: StateFlow<List<Inspiration>> = _inspirations
    }
