package com.example.kode_test_app.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.kode_test_app.domain.model.User

@Database(
    entities = [User::class],
    version = 1
)
abstract class UserDatabase: RoomDatabase() {

    abstract val dao: UserDao
}