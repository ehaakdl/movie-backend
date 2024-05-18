package com.movie.backend.utils;

public class CommonUtils {
    public static int getTotalPage(int total, int itemPerPage) {
        if (total <= 0) {
                return 1;
        }
        if (itemPerPage <= 0 || itemPerPage > total) {
                return 1;
        }

        if (total % itemPerPage > 0) {
                return total / itemPerPage + 1;
        } else {
                return total / itemPerPage;
        }
}

}
