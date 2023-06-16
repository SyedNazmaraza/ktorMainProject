package com.example.utils

import io.ktor.http.*
import kotlinx.serialization.Serializable


@Serializable
sealed class BaseResponse<T>{
    @Serializable
    data class SuccessResponse<T>(var data:T?=null,var mes:String):BaseResponse<T>()
    @Serializable
    data class ErrorResponse<T>(var data:T?=null,var mes:String ):BaseResponse<T>()

}

