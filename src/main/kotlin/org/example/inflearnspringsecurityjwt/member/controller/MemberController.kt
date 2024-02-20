package org.example.inflearnspringsecurityjwt.member.controller

import jakarta.validation.Valid
import org.example.inflearnspringsecurityjwt.common.authority.TokenInfo
import org.example.inflearnspringsecurityjwt.common.dto.BaseResponse
import org.example.inflearnspringsecurityjwt.common.dto.CustomUser
import org.example.inflearnspringsecurityjwt.member.dto.LoginDto
import org.example.inflearnspringsecurityjwt.member.dto.MemberDtoRequest
import org.example.inflearnspringsecurityjwt.member.dto.MemberDtoResponse
import org.example.inflearnspringsecurityjwt.member.service.MemberService
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*

@RequestMapping("/api/member")
@RestController
class MemberController(
    private val memberService: MemberService
) {
    /**
     * 회원가입
     */
    @PostMapping("/signup")
    fun signUp(@RequestBody @Valid memberDtoRequest: MemberDtoRequest): BaseResponse<Unit> {
        val resultMsg: String = memberService.signUp(memberDtoRequest)
        return BaseResponse(message = resultMsg)
    }
    /**
     * 로그인
     */
    @PostMapping("/login")
    fun login(@RequestBody @Valid loginDto: LoginDto): BaseResponse<TokenInfo> {
        val tokenInfo = memberService.login(loginDto)
        return BaseResponse(data = tokenInfo)
    }

    /*
    * 내 정보 보기
    * */
    @GetMapping("/info")
    fun searchMyInfo(): BaseResponse<MemberDtoResponse> {
        val userId = (SecurityContextHolder.getContext().authentication.principal as CustomUser).userId
        val response = memberService.searchMyInfo(userId)
        return BaseResponse(data = response)
    }

    /*
    * 내 정보 수정
    * */
    @PutMapping("/info")
    fun saveMyInfo(@RequestBody @Valid memberDtoRequest: MemberDtoRequest): BaseResponse<Unit> {
        val userId = (SecurityContextHolder.getContext().authentication.principal as CustomUser).userId
        memberDtoRequest.id = userId
        val resultMsg: String = memberService.saveMyInfo(memberDtoRequest)
        return BaseResponse(message = resultMsg)
    }

}

