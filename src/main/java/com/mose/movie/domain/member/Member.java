package com.mose.movie.domain.member;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
}
