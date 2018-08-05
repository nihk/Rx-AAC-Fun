package ca.nick.rxaacfun.data

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import ca.nick.rxaacfun.data.TheEntity.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class TheEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    var content: String = ""
) {
    companion object {
        const val TABLE_NAME = "the_table"
    }
}