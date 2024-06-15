package com.movie.backend.service.movie;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.movie.backend.model.entity.UserEntity;
import com.movie.backend.repository.MovieRepository;
import com.movie.backend.repository.NoticeCustomRepository;
import com.movie.backend.repository.NoticeHistoryRepository;
import com.movie.backend.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoticeTargetUserFinder {
    private final UserRepository userRepository;
    private final NoticeHistoryRepository noticeHistoryRepository;
    private final MovieRepository movieRepository;
    private final NoticeCustomRepository noticeCustomRepository;

    /**
     * 함수 역할
     * - 모든 유저중 알림 이력이 없거나
     * - 알림했던 시간 이후에 나온 영화가 있는경우 유저목록을 반환한다.
     */
    @Transactional
    public List<UserEntity> findByUnnotifiedOrNewMovie() {
        if (noticeHistoryRepository.count() == 0) {
            return userRepository.findAllByDeletedAtIsNull();
        }

        List<UserEntity> usersToNotifyWithNewMovies = findByNewMovieAfterLastNoticed();
        List<UserEntity> usersWithoutNoticeHistory = noticeCustomRepository.getUsersByEmptyNoticeHistory();

        return Stream.concat(usersToNotifyWithNewMovies.stream(), usersWithoutNoticeHistory.stream())
                .collect(Collectors.toList());
    }

    /**
     * 알림 이후 새로운 영화가 있는 유저들 찾기
     */
    @Transactional
    public List<UserEntity> findByNewMovieAfterLastNoticed() {
        return noticeHistoryRepository.findAll().stream()
                .filter(noticeHistory -> {
                    Date noticedAt = noticeHistory.getCreatedAt();
                    return movieRepository.countByCreatedAtAfter(noticedAt) > 0;
                })
                .map(noticeHistory -> noticeHistory.getUser())
                .collect(Collectors.toList());
    }
}
