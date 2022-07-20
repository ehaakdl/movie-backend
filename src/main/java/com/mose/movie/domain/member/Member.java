package com.mose.movie.domain.member;

import com.mose.movie.domain.board.Board;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
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
    @Builder.Default
    private List<Board> boards = new ArrayList<>();

    public static Member createMember(final String email, final String memberId
            , final Long point, final String nickname
            , final String memberPasswd
            , final eMemberJoinType joinType){

        return Member
                .builder()
                .memberId(memberId)
                .memberPasswd(memberPasswd)
                .email(email)
                .joinType(joinType)
                .nickname(nickname)
                .point(point)
                .build();
    }
/*
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return id.equals(member.id) && email.equals(member.email) && memberId.equals(member.memberId) && point.equals(member.point) && nickname.equals(member.nickname) && memberPasswd.equals(member.memberPasswd) && joinType == member.joinType && boards.equals(member.boards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, memberId, point, nickname, memberPasswd, joinType, boards);
    }*/
}
