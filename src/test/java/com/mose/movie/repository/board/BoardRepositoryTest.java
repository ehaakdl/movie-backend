package com.mose.movie.repository.board;

import com.mose.movie.domain.board.Board;
import com.mose.movie.domain.member.Member;
import com.mose.movie.domain.member.eMemberJoinType;
import com.mose.movie.repository.member.MemberRepository;
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
class BoardRepositoryTest {
    @Autowired
    BoardRepository boardRepository;
    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("게시글 전체 조회")
    void findAll(){
//        given
        int MAX_SIZE = 10;
        Board[] boards = new Board[MAX_SIZE];
        for (int i = 0; i < MAX_SIZE; i++) {
            Member member = Member.createMemberFromPublicJoin(UUID.randomUUID().toString(),
                    UUID.randomUUID().toString(), 0L, "nickname", "memberPasswd", eMemberJoinType.NORMAL);
            memberRepository.save(member);

            member = Member.createMemberFromSocialJoin(UUID.randomUUID().toString(), UUID.randomUUID().toString(), 0L, eMemberJoinType.NAVER);
            memberRepository.save(member);

            Board board = Board.createBoard("contents", "title", member, STREAM);
            boards[i] = boardRepository.save(board);
        }


//        when
        List<Board> boardsByDB = boardRepository.findAll();
//        then
        assertThat(boardsByDB.size()).isEqualTo(MAX_SIZE);
        for (int i = 0; i < MAX_SIZE; i++) {
            assertThat(boards[i]).isEqualTo(boardsByDB.get(i));
        }
    }
}