-- --------------------------------------------------------
-- 호스트:                          127.0.0.1
-- 서버 버전:                        8.0.32 - MySQL Community Server - GPL
-- 서버 OS:                        Linux
-- HeidiSQL 버전:                  12.5.0.6677
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- movie 데이터베이스 구조 내보내기
USE `movie`;

-- movie.user definition

CREATE TABLE IF NOT EXISTS `user` (
	`id` INT(10) NOT NULL AUTO_INCREMENT,
	`email` VARCHAR(50) NOT NULL COLLATE 'utf8mb4_0900_ai_ci',
	`password` VARCHAR(50) NOT NULL COLLATE 'utf8mb4_0900_ai_ci',
	`created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	`deleted_at` DATETIME NULL DEFAULT NULL,
	PRIMARY KEY (`id`) USING BTREE
)
COLLATE='utf8mb4_0900_ai_ci'
ENGINE=InnoDB
;

CREATE TABLE IF NOT EXISTS `movie` (
	`id` INT(10) NOT NULL AUTO_INCREMENT,
	`api_provider_type` VARCHAR(100) NOT NULL DEFAULT '0' COMMENT 'api 제공자\r\n- kobis' COLLATE 'utf8mb4_0900_ai_ci',
	`kobis_movie_code` VARCHAR(100) NULL DEFAULT '0' COMMENT '영화 코드' COLLATE 'utf8mb4_0900_ai_ci',
	`kobis_movie_name` VARCHAR(100) NULL DEFAULT '0' COMMENT '영화명' COLLATE 'utf8mb4_0900_ai_ci',
	`kobis_movie_name_en` VARCHAR(100) NULL DEFAULT '0' COMMENT '영어 영화명' COLLATE 'utf8mb4_0900_ai_ci',
	`kobis_movie_product_year` VARCHAR(4) NULL DEFAULT '0' COMMENT '영화 제작년도' COLLATE 'utf8mb4_0900_ai_ci',
	`kobis_movie_open_date` VARCHAR(8) NULL DEFAULT '0' COMMENT '개봉일(yyyymmdd)' COLLATE 'utf8mb4_0900_ai_ci',
	`kobis_movie_type` VARCHAR(20) NULL DEFAULT '0' COMMENT '영화유형\r\nex) 장편' COLLATE 'utf8mb4_0900_ai_ci',
	`kobis_movie_product_status` VARCHAR(20) NULL DEFAULT '0' COMMENT '제작상태(prdtStatNm)' COLLATE 'utf8mb4_0900_ai_ci',
	`kobis_movie_genre` VARCHAR(50) NULL DEFAULT '0' COMMENT '전체장르' COLLATE 'utf8mb4_0900_ai_ci',
	`kobis_rep_genre_name` VARCHAR(50) NULL DEFAULT '0' COMMENT '대표장르' COLLATE 'utf8mb4_0900_ai_ci',
	`kobis_director_name` VARCHAR(50) NULL DEFAULT '0' COMMENT '영화감독명' COLLATE 'utf8mb4_0900_ai_ci',
	`created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	`deleted_at` DATETIME NULL DEFAULT NULL,
	PRIMARY KEY (`id`) USING BTREE,
	UNIQUE INDEX `kobis_movie_code` (`kobis_movie_code`) USING BTREE
)
COMMENT='제공받은 영화 정보를 담는 테이블\r\n현재 kobis에서 조회한 영화정보만 담는다.'
COLLATE='utf8mb4_0900_ai_ci'
ENGINE=InnoDB
;

CREATE TABLE IF NOT EXISTS `notice_history` (
	`id` INT(10) NOT NULL AUTO_INCREMENT,
	`type` VARCHAR(50) NOT NULL COMMENT '유형\r\n- email' COLLATE 'utf8mb4_0900_ai_ci',
	`user_id` INT(10) NOT NULL,
	`created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	PRIMARY KEY (`id`) USING BTREE,
	INDEX `FK1_USER_ID` (`user_id`) USING BTREE,
	CONSTRAINT `FK1_USER_ID` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON UPDATE NO ACTION ON DELETE NO ACTION
)
COMMENT='알림내역을 저장한다.'
COLLATE='utf8mb4_0900_ai_ci'
ENGINE=InnoDB
;
