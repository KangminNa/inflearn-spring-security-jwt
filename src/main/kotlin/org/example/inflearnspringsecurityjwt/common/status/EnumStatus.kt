package org.example.inflearnspringsecurityjwt.common.status

enum class Gender(val desc: String) {
    MAN(desc = "남"),
    WOMAN(desc = "여")
}

enum class ResultCode(val msg: String) {
    SUCCESS(msg = "항상 처리 되었습니다."),
    ERROR(msg = "에러가 발생했습니다.")
}