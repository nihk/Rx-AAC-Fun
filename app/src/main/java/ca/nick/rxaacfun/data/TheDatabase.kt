package ca.nick.rxaacfun.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

@Database(entities = [TheEntity::class], version = 1)
abstract class TheDatabase : RoomDatabase() {

    abstract fun theDao(): TheDao

    companion object {
        private const val NAME = "the_database.db"
        private var INSTANCE: TheDatabase? = null

        private fun create(context: Context): TheDatabase =
            Room.databaseBuilder(context.applicationContext, TheDatabase::class.java, NAME)
                .build()

        // Not threadsafe
        fun getInstance(context: Context): TheDatabase =
                INSTANCE ?: create(context).also { INSTANCE = it }
    }
}