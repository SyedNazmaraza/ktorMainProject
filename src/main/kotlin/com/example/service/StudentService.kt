package com.example.service


import com.example.model.studentsModel.Student
import com.example.model.studentsModel.StudentCredentials

interface StudentService {

    suspend fun registerUser(params:StudentCredentials): Student?
    suspend fun findUserByEmail(params: StudentCredentials):Student?
    suspend fun deleteUserByEmail(params: StudentCredentials): Student?
}