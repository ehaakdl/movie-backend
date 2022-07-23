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
@Table(name = "member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;

    @Column(name = "member_id")
    private String memberId;
    private Long point;
    private String nickname;

    @Column(name = "member_passwd")
    private String memberPasswd;

    @Enumerated(EnumType.STRING)
    @Column(name = "join_type")
    private eMemberJoinType joinType;

    @OneToMany(mappedBy = "member"
            ,cascade = CascadeType.ALL
            , fetch = FetchType.LAZY)
    private List<Board> boards;

    public static Member createMemberFromPublicJoin(final String email, final String memberId
            , final Long point, final String nickname
            , final String memberPasswd
            , final eMemberJoinType joinType){

        return Member
                .builder()
                .boards(new ArrayList<>())
                .memberId(memberId)
                .memberPasswd(memberPasswd)
                .email(email)
                .joinType(joinType)
                .nickname(nickname)
                .point(point)
                .build();
    }

    public static Member createMemberFromSocialJoin(final String email
                                                    ,final String nickname
            , final Long point
            , final eMemberJoinType joinType){

        return Member
                .builder()
                .boards(new ArrayList<>())
                .email(email)
                .nickname(nickname)
                .joinType(joinType)
                .point(point)
                .build();
    }
}
