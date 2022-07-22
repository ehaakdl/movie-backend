package com.mose.movie.repository.member;

import com.mose.movie.domain.member.Member;
import com.mose.movie.domain.member.eMemberJoinType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Rollback(value = false)
class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("일반 가입 회원을 아이디로 조회")
    void findMemberId() {
//        given
        Member member = Member.createMemberFromPublicJoin("ehaakdl",
                "wqewq", 0L, "nickname", "memberPasswd", eMemberJoinType.NORMAL);
        member = memberRepository.save(member);

//        when
        List<Member> members = memberRepository.findByMemberId("wqewq");

//        then
        assertThat(members.size()).isEqualTo(1);
        assertThat(member).isEqualTo(members.get(0));
    }
}