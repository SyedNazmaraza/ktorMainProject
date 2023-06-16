package com.example.entity

import org.jetbrains.exposed.sql.Table

object StudentTable:Table("student") {
    val id = integer("id").autoIncrement()
    val userName = varchar("userName",256)
    val email = varchar("email",256)
    val password = varchar("password",256)
    override val primaryKey= PrimaryKey(id)

}