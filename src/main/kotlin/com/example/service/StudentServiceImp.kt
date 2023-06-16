package com.example.service

import com.example.database.DatabaseFactory
import com.example.entity.StudentTable
import com.example.model.studentsModel.Student
import com.example.model.studentsModel.StudentCredentials
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.statements.InsertStatement

class StudentServiceImp : StudentService {
    override suspend fun registerUser(params: StudentCredentials): Student? {
        var statement: InsertStatement<Number>? = null;
        DatabaseFactory.dbQuery {
            statement = StudentTable.insert {
                it[this.userName] = params.userName
                it[this.email] = params.email
                it[this.password] = params.password
            }
        }
       return rowToUser(statement?.resultedValues?.get(0))
    }

    override suspend fun findUserByEmail(params: StudentCredentials): Student? {
        var student = DatabaseFactory.dbQuery {
            StudentTable.select { StudentTable.email eq params.email }.map { rowToUser(it) }.singleOrNull()
        }
        return student
    }

    override suspend fun deleteUserByEmail(params: StudentCredentials): Student? {
        TODO("Not yet implemented")
    }


    private fun rowToUser(row:ResultRow?):Student?{
        return if(row==null) null else Student(row[StudentTable.id],row[StudentTable.userName],row[StudentTable.email])
    }
}