DROP DATABASE IF EXISTS bithive;

CREATE SCHEMA bithive DEFAULT CHARACTER SET utf8mb4;

DROP ROLE IF EXISTS 'dev_role';
DROP ROLE IF EXISTS  'user_role';

CREATE ROLE 'dev_role';
CREATE ROLE 'user_role';

GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, ALTER, DROP, TRIGGER ON bithive.* TO 'dev_role';
GRANT SELECT, INSERT, UPDATE, DELETE, TRIGGER ON bithive.* TO 'user_role';

DROP USER IF EXISTS 'bithive_dev'@'localhost';
DROP USER IF EXISTS 'bithive_user'@'localhost';

CREATE USER 'bithive_dev'@'localhost' IDENTIFIED BY 'bithive';
CREATE USER 'bithive_user'@'localhost' IDENTIFIED BY 'bithive';

GRANT 'dev_role' TO 'bithive_dev'@'localhost';
GRANT 'user_role' TO 'bithive_user'@'localhost';

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





