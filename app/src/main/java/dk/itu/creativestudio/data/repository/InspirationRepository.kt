package dk.itu.creativestudio.data.repository

import dk.itu.creativestudio.data.local.InspirationDao
import dk.itu.creativestudio.data.model.Inspiration
import kotlinx.coroutines.flow.Flow

class InspirationRepository(
    private val dao: InspirationDao
) {

    fun getAllInspirations(): Flow<List<Inspiration>> {
        return dao.getAll()
    }

    suspend fun insertInspiration(inspiration: Inspiration) {
        dao.insert(inspiration)
    }

    suspend fun updateInspiration(inspiration: Inspiration) {
        dao.update(inspiration)
    }

    suspend fun deleteInspiration(inspiration: Inspiration) {
        dao.delete(inspiration)
    }

    suspend fun getCount(): Int {
        return dao.getCount()
    }
}