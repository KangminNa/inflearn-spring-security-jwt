package org.example.inflearnspringsecurityjwt.member.service

import jakarta.transaction.Transactional
import org.example.inflearnspringsecurityjwt.common.authority.JwtTokenProvider
import org.example.inflearnspringsecurityjwt.common.authority.TokenInfo
import org.example.inflearnspringsecurityjwt.common.exception.InvalidInputException
import org.example.inflearnspringsecurityjwt.common.status.ROLE
import org.example.inflearnspringsecurityjwt.member.dto.LoginDto
import org.example.inflearnspringsecurityjwt.member.dto.MemberDtoRequest
import org.example.inflearnspringsecurityjwt.member.entitiy.Member
import org.example.inflearnspringsecurityjwt.member.entitiy.MemberRole
import org.example.inflearnspringsecurityjwt.member.repository.MemberRepository
import org.example.inflearnspringsecurityjwt.member.repository.MemberRoleRepository
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.stereotype.Service

@Transactional
@Service
class MemberService(
    private val memberRepository: MemberRepository,
    private val memberRoleRepository: MemberRoleRepository,
    private val authenticationManagerBuilder: AuthenticationManagerBuilder,
    private val jwtTokenProvider: JwtTokenProvider,

    ) {
    /**
     * 회원가입
     */
    fun signUp(memberDtoRequest: MemberDtoRequest): String {
        // ID 중복 검사
        var member: Member? = memberRepository.findByLoginId(memberDtoRequest.loginId)
        if (member != null) {
            throw InvalidInputException("loginId", "이미 등록된 ID 입니다.")
        }

        member = memberDtoRequest.toEntity()
        memberRepository.save(member)

        val memberRole: MemberRole = MemberRole(null, ROLE.MEMBER, member)
        memberRoleRepository.save(memberRole)

        return "회원가입이 완료되었습니다."
    }

    /**
     * 로그인 -> 토큰 발행
     */
    fun login(loginDto: LoginDto): TokenInfo {
        val authenticationToken = UsernamePasswordAuthenticationToken(loginDto.loginId, loginDto.password)
        val authentication = authenticationManagerBuilder.`object`.authenticate(authenticationToken)

        return jwtTokenProvider.createToken(authentication)
    }


}