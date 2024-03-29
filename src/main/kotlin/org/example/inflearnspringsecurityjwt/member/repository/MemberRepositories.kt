package org.example.inflearnspringsecurityjwt.member.repository



import org.example.inflearnspringsecurityjwt.member.entitiy.Member
import org.example.inflearnspringsecurityjwt.member.entitiy.MemberRole
import org.springframework.data.jpa.repository.JpaRepository


interface MemberRepository : JpaRepository<Member, Long> {
    fun findByLoginId(loginId: String): Member?
}

interface MemberRoleRepository : JpaRepository<MemberRole, Long>

