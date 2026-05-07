package dk.itu.creativestudio

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Inspiration::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun inspirationDao(): InspirationDao
}