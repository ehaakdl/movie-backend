package com.mose.movie.domain.file;

import com.mose.movie.domain.board.Board;
import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "file_type")
@Table(name = "File")
public abstract class File {

    public File(Long fileSz, String originName, String identifyName
            , String filePath, Board board) {
        this.fileSz = fileSz;
        this.originName = originName;
        this.identifyName = identifyName;
        this.filePath = filePath;
        this.board = board;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "file_size")
    private Long fileSz;

    @Column(name = "origin_name")
    private String originName;

    @Column(name = "identify_name")
    private String identifyName;

    @Column(name = "file_path")
    private String filePath;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_identify")
    private Board board;
}
