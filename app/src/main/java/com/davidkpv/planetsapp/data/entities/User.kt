package com.davidkpv.planetsapp.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey val id: Int = 1,
    val name: String,
    val lastName: String,
    val birthDate: String,
    val country: String,
)
