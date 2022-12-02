package com.example.repro.db

import androidx.room.RawQuery
import androidx.sqlite.db.SimpleSQLiteQuery
import androidx.sqlite.db.SupportSQLiteQuery

/**
 * Handles functionality common between all DAOs that return [AppEntity]s. For example, [BaseDao] can implement
 * [findByIds] since all [AppEntity]s have an ID property.
 */
interface BaseDao<T : AppEntity> {
    val tableName: String

    @RawQuery
    fun executeSql(query: SupportSQLiteQuery): List<T>

    // This can't be an abstract method with the `@Query` annotation because the underlying query needs to use
    // `$tableName`, which is not a compile-time constant
    fun findByIds(ids: List<Int>): List<T> {
        if (ids.isEmpty()) {
            return emptyList()
        }

        val query = SimpleSQLiteQuery("SELECT * FROM $tableName WHERE id IN (${ids.joinToString(",")})")
        return executeSql(query)
    }

    fun thisWorks() = emptyList<T>()

    fun thisWorksToo(entity: T) = emptyList<T>()

    fun thisBreaks(entities: List<T>) = emptyList<T>()
}

interface AppEntity {
    val id: Int
}