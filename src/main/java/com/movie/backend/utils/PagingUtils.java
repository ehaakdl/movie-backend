package com.movie.backend.utils;

public class PagingUtils {
        /**
         * 페이지 정보와 데이터 사이즈를 받아 offset를 구한다.
         * @param totalCount
         * @param page
         * @param pageSize
         * @return
         */
        public static int getOffset(long totalCount, int page, int pageSize) {
                if (totalCount < 0 || page <= 0 || pageSize <= 0) {
                        return 0;
                }

                /**
                 * 0 ~ 2 - 1p, 2per
                 * 2 ~ 4 - 2p, 2per
                 * 4 ~ 6 - 3p, 2per
                 */
                int offset = pageSize * (page - 1);
                return offset;
        }

        /**
         * 전체 페이지 갯수를 반환한다.
         * 값을 구할 수 없는 상태는 1로 반환한다. 
         * @param totalCount
         * @param pageSize
         * @return
         */
        public static long getTotalPage(long totalCount, int pageSize) {
                if (totalCount <= 0) {
                        return 1;
                }
                if (pageSize <= 0 || pageSize > totalCount) {
                        return 1;
                }

                if (totalCount % pageSize > 0) {
                        return totalCount / pageSize + 1;
                } else {
                        return totalCount / pageSize;
                }
        }

}
