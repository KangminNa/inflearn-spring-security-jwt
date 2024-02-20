package org.example.inflearnspringsecurityjwt.common.authority

data class TokenInfo(
    val grantType: String,
    val accessToken: String,
)