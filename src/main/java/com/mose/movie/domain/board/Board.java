package com.mose.movie.domain.board;

import com.mose.movie.domain.member.Member;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Entity
@Builder(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "write_date")
    private LocalDate writeDate;

    @Column(name = "update_date")
    private LocalDate updateDate;

    @Column(name = "writer_nickname")
    private String writerNickName;

    private String title;
    private String contents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public static Board createBoard(final String writerNickName
            , final String contents
            , final String title
            , final Member member){
        return Board
                .builder()
                .member(member)
                .title(title)
                .contents(contents)
                .updateDate(LocalDate.now())
                .writeDate(LocalDate.now())
                .writerNickName(writerNickName)
                .build();
    }
}
