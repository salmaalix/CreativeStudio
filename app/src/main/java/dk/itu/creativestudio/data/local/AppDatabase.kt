package dk.itu.creativestudio.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import dk.itu.creativestudio.data.model.Inspiration

@Database(entities = [Inspiration::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun inspirationDao(): InspirationDao
}