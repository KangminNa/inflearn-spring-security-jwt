package org.example.inflearnspringsecurityjwt.common.dto

import org.example.inflearnspringsecurityjwt.common.status.ResultCode


data class BaseResponse<T>(
    val resultCode: String = ResultCode.SUCCESS.name,
    val data: T? = null,
    val message: String = ResultCode.SUCCESS.msg,
)