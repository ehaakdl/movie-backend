package com.mose.movie.domain.board;

import com.mose.movie.domain.member.Member;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Entity
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
}
