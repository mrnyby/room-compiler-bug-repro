package com.example.repro

import androidx.room.*
import com.example.repro.db.AppEntity
import com.example.repro.db.BaseDao

@Dao
interface UserDao : BaseDao<User> {
    override val tableName get() = "user"

    @Query("""
        SELECT *
        FROM user
        WHERE first_name LIKE :first
            AND last_name LIKE :last
        LIMIT 1
    """)
    fun findByName(first: String, last: String): User
}

@Entity
data class User(
    @PrimaryKey override val id: Int,
    @ColumnInfo(name = "first_name") val firstName: String?,
    @ColumnInfo(name = "last_name") val lastName: String?,
) : AppEntity