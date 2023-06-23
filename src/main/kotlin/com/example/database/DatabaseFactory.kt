package com.example.database

import com.example.entity.StudentTable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseFactory{
    var db1 = org.ktorm.database.Database.connect(
        url = "jdbc:mysql://localhost:3306/ktor",
        driver = "com.mysql.cj.jdbc.Driver",
        user = "root",
        password = "root")

    fun init(){
        var db = Database.connect(
            url="jdbc:postgresql://localhost:5432/",
            driver = "org.postgresql.Driver",
            user = "postgres",
            password = "root" )

        transaction(db){
            addLogger(StdOutSqlLogger)
            SchemaUtils.create(StudentTable)
        }
    }
    suspend fun <T> dbQuery(block: ()-> T): T {
        return withContext(Dispatchers.IO){
            transaction { block() }
        }
    }

}

//    private fun hikari(): HikariDataSource {
//
//        val config = HikariConfig()
//        config.driverClassName = "org.postgresql.Driver"
//        config.jdbcUrl = "jdbc:postgresql://localhost:5432/"
//        config.username = "postgres"
//        config.password = "root"
//        config.maximumPoolSize = 3
//        config.isAutoCommit =  false
//        config.transactionIsolation= "TRANSACTION_REPEATABLE_READ"
//        config.validate()
//        return HikariDataSource(config)
//    }
//
//
//}