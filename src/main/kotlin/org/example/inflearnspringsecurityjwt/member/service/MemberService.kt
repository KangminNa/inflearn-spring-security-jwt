package org.example.inflearnspringsecurityjwt.member.service

import jakarta.transaction.Transactional
import org.example.inflearnspringsecurityjwt.common.exception.InvalidInputException
import org.example.inflearnspringsecurityjwt.common.status.ROLE
import org.example.inflearnspringsecurityjwt.member.dto.MemberDtoRequest
import org.example.inflearnspringsecurityjwt.member.dto.MemberRole
import org.example.inflearnspringsecurityjwt.member.entitiy.Member
import org.example.inflearnspringsecurityjwt.member.repository.MemberRepository
import org.example.inflearnspringsecurityjwt.member.repository.MemberRoleRepository
import org.springframework.stereotype.Service

@Transactional
@Service
class MemberService(
    private val memberRepository: MemberRepository,
    private val memberRoleRepository: MemberRoleRepository,

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


}