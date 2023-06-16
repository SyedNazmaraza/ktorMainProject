package com.example.plugins

import com.example.model.studentsModel.StudentCredentials
import com.example.utils.BaseResponse
import com.example.service.StudentServiceImp
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.studentsRouting(){
    var service = StudentServiceImp()
    routing {
        route("/student"){
            post("/register"){
                var user = service.registerUser(call.receive<StudentCredentials>());
                println(user)
                if(user!=null){
                    var result = BaseResponse.SuccessResponse(data = user, mes = "Success")
                    call.respond(HttpStatusCode.OK,result)
                }
                else {
                    var result = BaseResponse.ErrorResponse(data = "null", mes = "Failure")
                    call.respond(HttpStatusCode.BadRequest,result)
                }

            }
            get ("/login"){

            }
        }
    }
}