package com.mose.movie.domain.file;

import com.mose.movie.domain.board.Board;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("STREAM")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Movie extends File{
    @Column(name = "stream_link")
    private String streamLink;

    @Builder
    public Movie(Long fileSz, String originName, String identifyName, String filePath, Board board, String streamLink) {
        super(fileSz, originName, identifyName, filePath, board);
        this.streamLink = streamLink;
    }
}
