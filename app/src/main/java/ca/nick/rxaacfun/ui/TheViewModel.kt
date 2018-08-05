package ca.nick.rxaacfun.ui

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import ca.nick.rxaacfun.data.TheDatabase
import ca.nick.rxaacfun.data.TheEntity
import java.util.*

class TheViewModel(application: Application) : AndroidViewModel(application) {

    private val theDatabase = TheDatabase.getInstance(application)

    fun entities() = theDatabase.theDao().getTheEntities()

    fun createEntity() {
        val entity = TheEntity(content = UUID.randomUUID().toString())
        theDatabase.theDao().insertOrUpdate(entity)
    }

    fun deleteAll() {
        theDatabase.theDao().nuke()
    }

    fun delete(id: Long) {
        theDatabase.theDao().delete(id)
    }
}