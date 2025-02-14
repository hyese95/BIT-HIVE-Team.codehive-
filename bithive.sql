DROP DATABASE IF EXISTS bithive;

CREATE SCHEMA bithive DEFAULT CHARACTER SET utf8mb4;

DROP ROLE IF EXISTS 'dev_role';
DROP ROLE IF EXISTS  'user_role';

CREATE ROLE 'dev_role';
CREATE ROLE 'user_role';

GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, ALTER, DROP, TRIGGER, REFERENCES ON bithive.* TO 'dev_role';
GRANT SELECT, INSERT, UPDATE, DELETE, TRIGGER ON bithive.* TO 'user_role';

DROP USER IF EXISTS 'bithive_dev'@'localhost';
DROP USER IF EXISTS 'bithive_user'@'localhost';

CREATE USER 'bithive_dev'@'localhost' IDENTIFIED BY 'bithive';
CREATE USER 'bithive_user'@'localhost' IDENTIFIED BY 'bithive';

GRANT 'dev_role' TO 'bithive_dev'@'localhost';
SET DEFAULT ROLE 'dev_role' TO 'bithive_dev'@'localhost';
FLUSH PRIVILEGES;
GRANT 'user_role' TO 'bithive_user'@'localhost';

USE bithive;

SELECT 'CREATING DATABASE STRUCTURE' as 'INFO';


CREATE TABLE users (
                       user_no VARCHAR(10) PRIMARY KEY,
                       user_id VARCHAR(50) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       nickname VARCHAR(50) NOT NULL,
                       email VARCHAR(100) NOT NULL UNIQUE,
                       phone VARCHAR(20) NOT NULL,
                       privacy_agreements BOOLEAN NOT NULL DEFAULT false,
                       marketing_agreements BOOLEAN NOT NULL DEFAULT false,
                       created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                       profile_img_url VARCHAR(255),
                       nationality ENUM('KOREAN', 'JAPAN') NOT NULL,
                       gender ENUM('MALE', 'FEMALE') NOT NULL,
                       theme ENUM('LIGHT', 'DARK') NOT NULL DEFAULT 'LIGHT',
                       birth_date TIMESTAMP,
                       name VARCHAR(50) NOT NULL,
                       self_introduction TEXT,
                       role ENUM('USER', 'MANGER') NOT NULL DEFAULT 'USER'
);

CREATE TABLE notification_settings (
                       user_no VARCHAR(10) PRIMARY KEY,
                       volatility_yn BOOLEAN,
                       portfolio_yn	BOOLEAN,
                       target_price_yn	BOOLEAN,
                       trade_yn	BOOLEAN,
                       like_yn	BOOLEAN,
                       comment_yn	BOOLEAN,
                       reply_yn	BOOLEAN,
                       follower_yn	BOOLEAN
);

CREATE TABLE target_price_alerts (
                                     target_price_alerts_id VARCHAR(255) PRIMARY KEY,
                                     user_no VARCHAR(255),
                                     market VARCHAR(255),
                                     target_price DOUBLE,
                                     FOREIGN KEY (user_no) REFERENCES users(user_no)
);

CREATE TABLE volatility_alerts (
        volatility_alerts_id  VARCHAR(255) PRIMARY KEY,
        user_no VARCHAR(255),
        market VARCHAR(255),
        FOREIGN KEY (user_no) REFERENCES users(user_no)
);

CREATE TABLE coin_transactions (
        trans_no VARCHAR(255) PRIMARY KEY,
        user_no VARCHAR(255),
        market VARCHAR(255),
        transaction_type Enum('매수', '매도'),
        price DOUBLE,
        transaction_cnt DOUBLE,
        transaction_date TIMESTAMP,
        transaction_state Enum('체결', '미체결'),
        FOREIGN KEY (user_no) REFERENCES users(user_no)
);

CREATE TABLE favorite_markets (
        favorite_coin_Id VARCHAR(255) PRIMARY KEY,
        user_no VARCHAR(255),
        market VARCHAR(255),
        list_id VARCHAR(255),
        sort_order Enum('오름차순', '내림차순'),
        FOREIGN KEY (user_no) REFERENCES users(user_no),
        FOREIGN KEY (list_id) REFERENCES favorite_markets_folders(list_id)
);

CREATE TABLE favorite_markets_folders (
                list_id	VARCHAR(255) PRIMARY KEY,
                user_id	VARCHAR(255),
                list_name VARCHAR(255),
                FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE follows (
                         follower_user_no  VARCHAR(50) NOT NULL,
                         following_user_no VARCHAR(50) NOT NULL,
                         following_date    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

                         PRIMARY KEY (follower_user_no, following_user_no),
                         CONSTRAINT fk_follows_follower FOREIGN KEY (follower_user_no) REFERENCES users(user_no) ON DELETE CASCADE,
                         CONSTRAINT fk_follows_following FOREIGN KEY (following_user_no) REFERENCES users(user_no) ON DELETE CASCADE
);



