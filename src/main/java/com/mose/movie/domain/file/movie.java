package com.mose.movie.domain.file;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("STREAM")
@Getter
@Table(name = "File")
public class movie extends File{
    @Column("stream_link")
    private String streamLink;
}
