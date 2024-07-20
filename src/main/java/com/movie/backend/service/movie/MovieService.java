package com.movie.backend.service.movie;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.movie.backend.model.dto.MovieDTO;
import com.movie.backend.model.dto.MoviesDTO;
import com.movie.backend.model.entity.eMovieApiProviderType;
import com.movie.backend.model.request.MovieSearchRequest;
import com.movie.backend.model.response.MovieSearchItemResponse;
import com.movie.backend.model.response.MovieSearchResponse;
import com.movie.backend.repository.MovieCustomRepository;
import com.movie.backend.utils.PagingUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieCustomRepository movieCustomRepository;

    @Transactional
    public MovieSearchResponse search(MovieSearchRequest request) {
        MoviesDTO searchResult = movieCustomRepository.search(request);
        long totalCount = searchResult.getTotalCount();
        int pageSize = request.getPageSize();
        eMovieApiProviderType providerType = request.getProvider();
        long totalPage = PagingUtils.getTotalPage(totalCount, pageSize);
        List<MovieDTO> movies = searchResult.getMovies();

        List<MovieSearchItemResponse> items = movies.stream()
                .map(item -> {
                    return MovieSearchItemResponse.convertToMovieSearchItemResponseByApiProviderType(item,
                            providerType);
                })
                .collect(Collectors.toList());

        return new MovieSearchResponse(totalCount, totalPage, items);
    }
}
