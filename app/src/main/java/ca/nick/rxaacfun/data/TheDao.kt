package ca.nick.rxaacfun.data

import android.arch.persistence.room.*
import io.reactivex.Flowable

@Dao
abstract class TheDao {

    @Query("SELECT * FROM ${TheEntity.TABLE_NAME}")
    abstract fun getTheEntities(): Flowable<List<TheEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertOrUpdate(vararg entities: TheEntity)

    @Query("DELETE FROM ${TheEntity.TABLE_NAME}")
    abstract fun nuke()
}