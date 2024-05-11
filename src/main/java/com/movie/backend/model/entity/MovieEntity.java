package com.movie.backend.model.entity;

import com.movie.backend.model.entity.audit.AuditEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Table(name = "movie")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovieEntity extends AuditEntity {
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
