package org.example.inflearnspringsecurityjwt.member.dto

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import org.example.inflearnspringsecurityjwt.common.annotation.ValidEnum
import org.example.inflearnspringsecurityjwt.common.status.Gender
import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class MemberDtoRequest(
    private val id: Long?,

    @field:NotBlank
    @JsonProperty("loginId")
    private val _loginId: String?,

    @field:NotBlank
    @field:Pattern(
        regexp = "^([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))$",
        message = "날짜형식(YYYY-MM-DD)을 확인해주세요"
    )
    @JsonProperty("password")
    private val _password: String?,

    @field:NotBlank
    @JsonProperty("name")
    private val _name: String?,

    @field:NotBlank
    @JsonProperty("birthDate")
    private val _birthDate: String?,

    @field:NotBlank
    @field:ValidEnum(enumClass = Gender::class, message = "MAN이나 WOMAN 둘 중 하나를 선택해 주세요")
    @JsonProperty("gender")
    private val _gender: String?,

    @field:NotBlank
    @JsonProperty("email")
    private val _email: String?,
) {
    val loginId: String
        get() = _loginId!!
    val password: String
        get() = _password!!
    val name: String
        get() = _name!!
    val birthDate: LocalDate
        get() = _birthDate!!.toLocalDate()
    val gender: Gender
        get() = Gender.valueOf(_gender!!)

    val email: String
        get() = _email!!

    private fun String.toLocalDate() : LocalDate =
        LocalDate.parse(this, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
}
