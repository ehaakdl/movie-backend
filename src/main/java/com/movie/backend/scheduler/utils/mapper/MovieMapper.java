package com.movie.backend.scheduler.utils.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.movie.backend.model.entity.MovieEntity;
import com.movie.backend.model.entity.eMovieApiProviderType;
import com.movie.backend.scheduler.model.response.kobis.KobisCompanyResponse;
import com.movie.backend.scheduler.model.response.kobis.KobisDirectorResponse;
import com.movie.backend.scheduler.model.response.kobis.KobisMovieResponse;

@Mapper(componentModel = "spring", uses = {
        KobisDirectorResponse.class,
        KobisCompanyResponse.class
})
public interface MovieMapper {
    // TODO response 벌크처리
    @Mapping(source = "response.movieCd", target = "kobisMovieCode")
    @Mapping(source = "response.movieNm", target = "kobisMovieName")
    @Mapping(source = "response.movieNmEn", target = "kobisMovieNameEn")
    @Mapping(source = "response.prdtYear", target = "kobisMovieProductYear")
    @Mapping(source = "response.openDt", target = "kobisMovieOpenDate")
    @Mapping(source = "response.typeNm", target = "kobisMovieType")
    @Mapping(source = "response.prdtStatNm", target = "kobisMovieProductStatus")
    @Mapping(source = "response.genreAlt", target = "kobisMovieGenre")
    @Mapping(source = "response.repGenreNm", target = "kobisRepGenreName")
    @Mapping(source = "type", target = "apiProviderType")
    // TODO director, company 맵핑 필요
    MovieEntity toMovieEntity(KobisMovieResponse response, eMovieApiProviderType type);

}
