package com.mose.movie.repository.member;

import com.mose.movie.domain.member.Member;
import com.mose.movie.domain.member.eMemberJoinType;
import com.mose.movie.repository.board.BoardRepository;
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

    @Autowired
    BoardRepository boardRepository;

    @Test
    @DisplayName("회원 추가 후 MemberId로 조회 및 값 비교 테스트")
    void createMember() {
        Member member = Member.createMember("ehaakdl@gamilc.com",
                "ehaakdl", 0L, "ehaakdl", "1234", eMemberJoinType.NORMAL);
        memberRepository.save(member);

        List<Member> members = memberRepository.findByMemberId("ehaakdl");
        for (Member member2 : members) {

            Assertions.assertThat(member.getMemberId()).isEqualTo(member2.getMemberId());
            Assertions.assertThat(member.getMemberPasswd()).isEqualTo(member2.getMemberPasswd());
            Assertions.assertThat(member.getEmail()).isEqualTo(member2.getEmail());
            Assertions.assertThat(member.getJoinType()).isEqualTo(member2.getJoinType());
            Assertions.assertThat(member.getNickname()).isEqualTo(member2.getNickname());
            Assertions.assertThat(member.getPoint()).isEqualTo(member2.getPoint());
        }

    }
}