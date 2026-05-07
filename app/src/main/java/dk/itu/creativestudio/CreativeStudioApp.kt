package dk.itu.creativestudio

import android.app.Application
import androidx.room.Room

class CreativeStudioApp : Application() {

    val database: AppDatabase by lazy {
        Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "inspiration_db"
        ).build()
    }
}