package com.mose.movie.domain.member;

import com.mose.movie.domain.board.Board;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Member {
    @Id
    @GeneratedValue
    private Long id;
    private String email;

    @Column(name = "member_id")
    private String memberId;
    private Long point;
    private String nickname;

    @Column(name = "member_passwd")
    private String memberPasswd;

    @Enumerated(EnumType.STRING)
    private eMemberJoinType joinType;

    @OneToMany(mappedBy = "member")
    private List<Board> boards = new ArrayList<>();
}
