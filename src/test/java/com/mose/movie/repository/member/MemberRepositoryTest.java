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

    @Test
    @DisplayName("회원 아이디 조회")
    void findMemberId() {
//        given
        Member member = Member.createMember("ehaakdl",
                "wqewq", 0L, "nickname", "memberPasswd", eMemberJoinType.NORMAL);
        member = memberRepository.save(member);

//        when
        List<Member> members = memberRepository.findByMemberId("wqewq");

//        then
        Assertions.assertThat(members.size()).isEqualTo(1);
        Assertions.assertThat(member).isEqualTo(members.get(0));
    }
}