package com.mose.movie.repository.board;

import com.mose.movie.domain.board.Board;
import com.mose.movie.domain.member.Member;
import com.mose.movie.domain.member.eMemberJoinType;
import com.mose.movie.repository.member.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@SpringBootTest
@Transactional
class BoardRepositoryTest {
    @Autowired
    BoardRepository boardRepository;
    @Autowired
    MemberRepository memberRepository;

    @BeforeEach
    void createBoard(){

    }

    @Test
    @DisplayName("게시글 전체 조회")
    void findAll(){
        //        given
        int MAX_SIZE = 10;
        Board[] boards = new Board[MAX_SIZE];
        for (int i = 0; i < MAX_SIZE; i++) {
            Member member = Member.createMember(UUID.randomUUID().toString(),
                    UUID.randomUUID().toString(), 0L, "nickname", "memberPasswd", eMemberJoinType.NORMAL);
            member = memberRepository.save(member);

            Board board = Board.createBoard("nickname", "contents", "title", member);
            boards[i] = boardRepository.save(board);
        }
//        when
        List<Board> boardsByDB = boardRepository.findAll();
//        then
        Assertions.assertThat(boardsByDB.size()).isEqualTo(MAX_SIZE);
        for (int i = 0; i < MAX_SIZE; i++) {
            Assertions.assertThat(boards[i]).isEqualTo(boardsByDB.get(i));
        }
    }
}