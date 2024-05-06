package com.movie.backend.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Table(name = "movie")
@Entity
public class MovieEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    @Enumerated(EnumType.STRING)
    private eMovieApiProviderType apiProviderType;

    @Column
    private String kobisMovieCode;

    @Column
    private String kobisMovieName;

    @Column
    private String kobisMovieNameEn;

    @Column
    private String kobisMovieProductYear;

    @Column
    private String kobisMovieOpenDate;

    @Column
    private String kobisMovieType;

    @Column
    private String kobisMovieProductStatus;

    @Column
    private String kobisMovieGenre;

    @Column
    private String kobisRepGenreName;

    @Column
    private String kobisDirectorName;
}
