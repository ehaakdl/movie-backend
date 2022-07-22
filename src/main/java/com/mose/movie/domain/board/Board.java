package com.mose.movie.domain.board;

import com.mose.movie.domain.member.Member;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@Builder(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "board")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "write_date")
    private LocalDateTime writeDate;

    @Column(name = "update_date")
    private LocalDateTime updateDate;

    private String title;
    private String contents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public static Board createBoard(final String contents
            , final String title
            , final Member member){
        return Board
                .builder()
                .member(member)
                .title(title)
                .contents(contents)
                .updateDate(LocalDateTime.now())
                .writeDate(LocalDateTime.now())
                .build();
    }
}
