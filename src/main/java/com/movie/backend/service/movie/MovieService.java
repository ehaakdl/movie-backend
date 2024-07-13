package com.movie.backend.service.movie;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.movie.backend.model.dto.MovieEntitiesDTO;
import com.movie.backend.model.dto.MovieSearchItemResponse;
import com.movie.backend.model.dto.MovieSearchRequest;
import com.movie.backend.model.dto.MovieSearchResponse;
import com.movie.backend.repository.MovieCustomRepository;
import com.movie.backend.utils.PagingUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieCustomRepository movieCustomRepository;

    @Transactional
    public MovieSearchResponse search(MovieSearchRequest request) {
        MovieEntitiesDTO searchResult = movieCustomRepository.search(request);

        long totalPage = PagingUtils.getTotalPage(searchResult.getTotalCount(), request.getPageSize());
        List<MovieSearchItemResponse> items = searchResult.getMovies().stream().map(item -> {
            return MovieSearchItemResponse.convertToMovieSearchItemResponseByApiProviderType(item,
                    request.getProvider());
        }).collect(Collectors.toList());

        return new MovieSearchResponse(searchResult.getTotalCount(), totalPage, items);
    }
}
