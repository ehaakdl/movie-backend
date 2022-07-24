package com.mose.movie.domain.file;

import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "file_type")
@Table(name = "File")
public abstract class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "file_size")
    private long fileSz;

    @Column(name = "origin_name")
    private String originName;

    @Column(name = "identify_name")
    private String identifyName;

    @Column(name = "file_path")
    private String filePath;

    @Column(name = "stream_link")
    private String streamLink;
}
