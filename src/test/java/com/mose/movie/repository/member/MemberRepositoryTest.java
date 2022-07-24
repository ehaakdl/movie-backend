package com.mose.movie.repository.member;

import com.mose.movie.domain.board.Board;
import com.mose.movie.domain.member.Member;
import com.mose.movie.domain.member.eMemberJoinType;
import com.mose.movie.repository.board.BoardRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static com.mose.movie.domain.board.eBoardType.STREAM;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    BoardRepository boardRepository;

    private Member createMember(eMemberJoinType joinType){
        Member member;
        if (joinType == eMemberJoinType.NAVER) {
            member = Member.createMemberFromSocialJoin(UUID.randomUUID().toString().substring(0, 20),
                    UUID.randomUUID().toString().substring(0, 20), 0L, eMemberJoinType.NORMAL);
            return memberRepository.save(member);
        }
        member = Member.createMemberFromPublicJoin(UUID.randomUUID().toString().substring(0, 20),
                UUID.randomUUID().toString().substring(0, 20), 0L, UUID.randomUUID().toString().substring(0, 20), "memberPasswd", eMemberJoinType.NORMAL);
        return memberRepository.save(member);
    }

    @Test
    @DisplayName("회원 아이디로 조회")
    void findMemberId() {
//        given
        Member member = createMember(eMemberJoinType.NORMAL);

//        when
        List<Member> members = memberRepository.findByMemberId(member.getMemberId());

//        then
        assertThat(members.size()).isEqualTo(1);
        assertThat(member).isEqualTo(members.get(0));
    }

    @Test
    @DisplayName("회원 이메일로 조회")
    void findMemberEmail() {
//        given
        Member member = createMember(eMemberJoinType.NAVER);

//        when
        List<Member> members = memberRepository.findByEmail(member.getEmail());

//        then
        assertThat(members.size()).isEqualTo(1);
        assertThat(member).isEqualTo(members.get(0));
    }

    @Test
    @DisplayName("회원 탈퇴")
    void deletePublicMember() {

        //        given
        Member publicJoin = createMember(eMemberJoinType.NORMAL);
        Member socialJoin = createMember(eMemberJoinType.NAVER);

        //          when
        memberRepository.delete(socialJoin);
        memberRepository.delete(publicJoin);

        //        then
        List<Member> members = memberRepository.findByMemberId(publicJoin.getMemberId());
        assertThat(members.size()).isEqualTo(0);
        members = memberRepository.findByEmail(socialJoin.getEmail());
        assertThat(members.size()).isEqualTo(0);
    }

    @Test
    @DisplayName("게시글 소유한 회원 삭제")
    void deletePublicMemberHaveBoard() {
        //given
        Member publicJoin = createMember(eMemberJoinType.NORMAL);
        Member socialJoin = createMember(eMemberJoinType.NAVER);

        Board boardByPublicJoin = boardRepository.save(Board.createBoard("contents", "title", publicJoin, STREAM));
        Board boardBySocialJoin = boardRepository.save(Board.createBoard("contents", "title", socialJoin, STREAM));

        //          when
        memberRepository.delete(socialJoin);
        memberRepository.delete(publicJoin);

        Board board = boardRepository.findById(boardByPublicJoin.getId()).orElse(null);
        assertThat(board).isEqualTo(null);
        board = boardRepository.findById(boardBySocialJoin.getId()).orElse(null);
        assertThat(board).isEqualTo(null);
    }
}