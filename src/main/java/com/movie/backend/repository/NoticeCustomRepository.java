package com.movie.backend.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.movie.backend.repository.model.NoticeHistoryVO;
import com.movie.backend.repository.model.UserNoticeHistoryVO;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class NoticeCustomRepository {

    private final JdbcTemplate jdbcTemplate;

    @Transactional
    public List<UserNoticeHistoryVO> getUsersNotifiedAfterOrNoNotifications() {
        String sql = "SELECT user.id as user_id, user.email, recent_notices.created_at as noticed_at " +
                "FROM user " +
                "LEFT JOIN (SELECT user_id, id, created_at, " +
                "                  ROW_NUMBER() OVER (PARTITION BY user_id ORDER BY id DESC) AS row_num " +
                "             FROM notice_history) AS recent_notices " +
                "ON recent_notices.row_num = 1 AND user.id = recent_notices.user_id " +
                "WHERE user.deleted_at IS NULL";

        return jdbcTemplate.query(sql, new ResultSetExtractor<List<UserNoticeHistoryVO>>() {
            @Override
            public List<UserNoticeHistoryVO> extractData(ResultSet rs) throws SQLException {
                List<UserNoticeHistoryVO> results = new ArrayList<>();
                while (rs.next()) {
                    Long userId = rs.getLong("user_id");
                    Date noticedAt = rs.getDate("noticed_at");
                    String email = rs.getString("email");
                    results.add(new UserNoticeHistoryVO(noticedAt, userId, email));
                }
                return results;
            }
        });

    }

    /**
     * getUsersNotifiedAfterOrNoNotifications 사용하여 유저를 가져올때 Jpa 에 종속된 Entity를 가져오지 못해서 saveAll
     * 함수를 사용할 수 없어 jdbcTemplate을 사용하여 bulkInsert함.
     * @param items
     */
    @Transactional
    // @Modifying 붙어야 insert sql 실행됨
    @Modifying
    public void bulkInsert(List<NoticeHistoryVO> items){
        String sql = "INSERT INTO notice_history (user_id, type) VALUES (?, ?)";
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setLong(1, items.get(i).getUserId());
                        ps.setString(2, items.get(i).getType().name());
                    }
                    @Override
                    public int getBatchSize() {
                        return items.size();
                    }
                });
    }
}
