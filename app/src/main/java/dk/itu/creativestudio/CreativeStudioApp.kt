package dk.itu.creativestudio

import android.app.Application
import androidx.room.Room
import dk.itu.creativestudio.data.local.AppDatabase

class CreativeStudioApp : Application() {

    val database: AppDatabase by lazy {
        Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "inspiration_db"
        ).build()
    }
}