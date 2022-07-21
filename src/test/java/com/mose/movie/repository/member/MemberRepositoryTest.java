package com.mose.movie.repository.member;

import com.mose.movie.domain.member.Member;
import com.mose.movie.domain.member.eMemberJoinType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    private final String memberId = "ehaakdl";
    private final String memberPasswd = "1234";
    private final String email = "ehaakdl@gamilc.com";
    private final eMemberJoinType joinType = eMemberJoinType.NORMAL;
    private final String nickname = "ehaakdl";
    private final Long point = 0L;

    @Test
    @DisplayName("회원 아이디 조회")
    void findMemberId() {
//        given
        Member member = Member.createMember(email,
                memberId, point, nickname, memberPasswd, joinType);
        member = memberRepository.save(member);

//        when
        List<Member> members = memberRepository.findByMemberId(memberId);

//        then
        Assertions.assertThat(members.size()).isEqualTo(1);
        Assertions.assertThat(member).isEqualTo(members.get(0));
    }
}