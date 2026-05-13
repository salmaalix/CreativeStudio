package dk.itu.creativestudio.data.local

import androidx.room.*
import dk.itu.creativestudio.data.model.Inspiration
import kotlinx.coroutines.flow.Flow

@Dao
interface InspirationDao {

    @Query("SELECT * FROM Inspiration")
    fun getAll(): Flow<List<Inspiration>>

    @Query("SELECT COUNT(*) FROM Inspiration")
    suspend fun getCount(): Int

    @Insert
    suspend fun insert(item: Inspiration)

    @Update
    suspend fun update(item: Inspiration)

    @Delete
    suspend fun delete(item: Inspiration)
}