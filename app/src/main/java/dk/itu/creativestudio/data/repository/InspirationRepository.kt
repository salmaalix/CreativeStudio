package dk.itu.creativestudio.data.repository

import dk.itu.creativestudio.data.local.InspirationDao
import dk.itu.creativestudio.data.model.Inspiration
import kotlinx.coroutines.flow.Flow

class InspirationRepository(
    private val dao: InspirationDao
) {
    fun getAllInspirations(): Flow<List<Inspiration>> = dao.getAll()

    suspend fun getById(id: Int): Inspiration? = dao.getById(id)

    suspend fun insertInspiration(inspiration: Inspiration) = dao.insert(inspiration)

    suspend fun updateInspiration(inspiration: Inspiration) = dao.update(inspiration)

    suspend fun deleteInspiration(inspiration: Inspiration) = dao.delete(inspiration)

    suspend fun seedIfEmpty() {
        if (dao.getCount() > 0) return
        dao.insert(
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
        dao.insert(
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
        dao.insert(
            Inspiration(
                title = "Wrap your earphones",
                notes = """
                Inspo: The beach
                Color: blue, gold and green
                Add: gold colored charms
                    - Star
                    - shells
                """.trimIndent(),
                imageUrl = "https://i.pinimg.com/originals/5c/d2/42/5cd24225d5cca7eef716846a2e489693.png",
                videoUrl = "https://www.youtube.com/watch?v=ESpum1w1vk0"
            )
        )
        dao.insert(
            Inspiration(
                title = "Personalized phonecase",
                notes = """
                Buy: clear phone case
                Print all things you love, cut them out and places them at back of the phonecase add photos too
                """.trimIndent(),
                imageUrl = "https://i.pinimg.com/originals/cd/a9/b3/cda9b34f98d9c469548eca148153f091.png",
                videoUrl = ""
            )
        )
    }
}