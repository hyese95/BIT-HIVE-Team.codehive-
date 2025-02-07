DROP DATABASE IF EXISTS bithive;
CREATE DATABASE IF NOT EXISTS bithive;
USE bithive;

SELECT 'CREATING DATABASE STRUCTURE' as 'INFO';


CREATE TABLE users (
                       user_no VARCHAR(10) PRIMARY KEY,  -- u001 형식
                       user_id VARCHAR(50) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,    -- 암호화된 비밀번호 저장을 고려해 넉넉한 길이
                       nickname VARCHAR(50) NOT NULL,
                       email VARCHAR(100) NOT NULL UNIQUE,
                       phone VARCHAR(20) NOT NULL,
                       privacy_agreements BOOLEAN NOT NULL DEFAULT false,
                       marketing_agreements BOOLEAN NOT NULL DEFAULT false,
                       created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                       profile_img_url VARCHAR(255),
                       nationality ENUM('KOREAN', 'JAPAN') NOT NULL,  -- 실제 사용할 값들로 수정 필요
                       gender ENUM('MALE', 'FEMALE') NOT NULL,
                       theme ENUM('LIGHT', 'DARK') NOT NULL DEFAULT 'LIGHT',  -- 실제 사용할 테마 값으로 수정 필요
                       birth_date TIMESTAMP,
                       name VARCHAR(50) NOT NULL,
                       self_introduction TEXT,
                       role ENUM('USER', 'MANGER') NOT NULL DEFAULT 'USER'
);