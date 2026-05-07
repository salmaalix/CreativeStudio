package dk.itu.creativestudio

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Inspiration(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val notes: String,
    val imageUrl: String,
    val videoUrl: String
)