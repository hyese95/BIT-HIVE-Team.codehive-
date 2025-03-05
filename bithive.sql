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


CREATE TABLE users(
    user_no INT auto_increment PRIMARY KEY,
    user_id VARCHAR(30) NOT NULL UNIQUE,
    password VARCHAR(50) NOT NULL,
    nickname VARCHAR(36) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    phone VARCHAR(15) NOT NULL,
    privacy_agreements BOOLEAN NOT NULL DEFAULT false,
    marketing_agreements BOOLEAN NOT NULL DEFAULT false,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    profile_img_url VARCHAR(255),
    nationality VARCHAR(20) NOT NULL,
    gender VARCHAR(20) NOT NULL,
    theme VARCHAR(20) NOT NULL DEFAULT 'LIGHT',
    birth_date DATE NOT NULL,
    name VARCHAR(50) NOT NULL,
    self_introduction VARCHAR(150),
    role ENUM('USER', 'MANGER') NOT NULL DEFAULT 'USER'
);

CREATE TABLE notification_settings(
    user_no INT auto_increment PRIMARY KEY,
    volatility_yn BOOLEAN DEFAULT FALSE,
    portfolio_yn BOOLEAN DEFAULT FALSE,
    target_price_yn	BOOLEAN DEFAULT FALSE,
    trade_yn BOOLEAN DEFAULT FALSE,
    like_yn	BOOLEAN DEFAULT FALSE,
    comment_yn BOOLEAN DEFAULT FALSE,
    reply_yn BOOLEAN DEFAULT FALSE,
    follower_yn	BOOLEAN DEFAULT FALSE
);

CREATE TABLE target_price_alerts(
    target_price_alerts_id INT auto_increment PRIMARY KEY,
    user_no INT,
    market VARCHAR(255),
    target_price DOUBLE,
    FOREIGN KEY(user_no) REFERENCES users(user_no)
);

CREATE TABLE volatility_alerts(
    volatility_alerts_id  INT auto_increment PRIMARY KEY,
    user_no INT,
    market VARCHAR(255),
    FOREIGN KEY(user_no) REFERENCES users(user_no)
);

CREATE TABLE coin_transactions(
    trans_no INT auto_increment PRIMARY KEY,
    user_no INT,
    market VARCHAR(255),
    transaction_type VARCHAR(20),
    price DOUBLE,
    transaction_cnt DOUBLE,
    transaction_date TIMESTAMP,
    transaction_state Enum('체결', '미체결'),
    FOREIGN KEY(user_no) REFERENCES users(user_no)
);

CREATE TABLE favorite_markets_folders(
    list_id	VARCHAR(255) PRIMARY KEY,
    user_no	VARCHAR(255),
    list_name VARCHAR(255),
    FOREIGN KEY(user_no) REFERENCES users(user_no)
);

CREATE TABLE favorite_markets(
    favorite_coin_Id VARCHAR(255) PRIMARY KEY,
    user_no VARCHAR(255),
    market VARCHAR(255),
    list_id VARCHAR(255),
    sort_order Enum('오름차순', '내림차순'),
    FOREIGN KEY(user_no) REFERENCES users(user_no),
    FOREIGN KEY(list_id) REFERENCES favorite_markets_folders(list_id)
);

CREATE TABLE follows(
    follower_user_no  VARCHAR(50) NOT NULL,
    following_user_no VARCHAR(50) NOT NULL,
    following_date    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY(follower_user_no, following_user_no),
    CONSTRAINT fk_follows_follower FOREIGN KEY(follower_user_no) REFERENCES users(user_no) ON DELETE CASCADE,
    CONSTRAINT fk_follows_following FOREIGN KEY(following_user_no) REFERENCES users(user_no) ON DELETE CASCADE
);

CREATE TABLE posts(
    post_no VARCHAR(255) PRIMARY KEY,
    user_no VARCHAR(255),
    post_cont VARCHAR(255),
    post_created_at TIMESTAMP,
    img_url VARCHAR(255)
);

ALTER TABLE posts
ADD CONSTRAINT fk_user_no
FOREIGN KEY (user_no) REFERENCES users(user_no);

CREATE TABLE comments(
    comment_no VARCHAR(255) PRIMARY KEY,
    post_no VARCHAR(255),
    user_no VARCHAR(255),
    comment_created_at TIMESTAMP,
    parent_no VARCHAR(255),
    comment_cont VARCHAR(255),
    FOREIGN KEY(post_no) REFERENCES posts(post_no),
    FOREIGN KEY(user_no) REFERENCES users(user_no)
);

ALTER TABLE comments
ADD CONSTRAINT fk_parent_comment
FOREIGN KEY (parent_no) REFERENCES comments(comment_no);

CREATE TABLE chatting_logs(
    chat_no VARCHAR(255) PRIMARY KEY,
    user_no VARCHAR(255),
    chat_created_at TIMESTAMP,
    content VARCHAR(255),
    FOREIGN KEY(user_no) REFERENCES users(user_no)
);

CREATE TABLE post_likes(
    user_no VARCHAR(255),
    post_no VARCHAR(255),
    PRIMARY KEY(user_no, post_no),
    like_type BOOLEAN,
    FOREIGN KEY(post_no) REFERENCES posts(post_no),
    FOREIGN KEY(user_no) REFERENCES users(user_no)
);

CREATE TABLE post_bookmarks(
    user_no VARCHAR(255),
    post_no VARCHAR(255),
    PRIMARY KEY(user_no, post_no),
    FOREIGN KEY(post_no) REFERENCES posts(post_no),
    FOREIGN KEY(user_no) REFERENCES users(user_no)
);

CREATE TABLE comment_likes(
    user_no VARCHAR(255),
    comment_no VARCHAR(255),
    PRIMARY KEY(user_no, comment_no),
    like_type BOOLEAN,
    FOREIGN KEY(user_no) REFERENCES users(user_no),
    FOREIGN KEY(comment_no) REFERENCES comments(comment_no)
);

CREATE TABLE password_change_logs(
    password_log_id VARCHAR(255) PRIMARY KEY,
    user_no VARCHAR(255),
    password VARCHAR(255),
    change_date TIMESTAMP,
    FOREIGN KEY(user_no) REFERENCES users(user_no)
);

CREATE TABLE login_logs(
    login_log_id VARCHAR(255) PRIMARY KEY,
    user_no VARCHAR(255),
    login_date TIMESTAMP,
    FOREIGN KEY(user_no) REFERENCES users(user_no)
);

CREATE TABLE exp_activity(
    activity_type VARCHAR(255) PRIMARY KEY,
    exp_amount INT,
    description VARCHAR(255)
);

CREATE TABLE exp_logs(
    exp_log_id VARCHAR(255) PRIMARY KEY,
    user_no VARCHAR(255),
    activity_type VARCHAR(255),
    created_at TIMESTAMP,
    FOREIGN KEY(user_no) REFERENCES users(user_no),
    FOREIGN KEY(activity_type) REFERENCES exp_activity(activity_type)
);

CREATE TABLE level_exp(
    level INT PRIMARY KEY,
    required_exp INT
);

CREATE TABLE guides(
    guide_no VARCHAR(255) PRIMARY KEY,
    guide_cont VARCHAR(255),
    guide_created_at TIMESTAMP,
    guide_title VARCHAR(255)
);

CREATE TABLE questions(
    question_no VARCHAR(255) PRIMARY KEY,
    user_no VARCHAR(255),
    question_cont VARCHAR(255),
    question_created_at TIMESTAMP,
    question_title VARCHAR(255),
    question_status Enum('완료', '미완료'),
    question_option Enum('계정', '일반', '포인트', '2차 인증', '매매', '건의사항', '제보', '회원신고'),
    FOREIGN KEY(user_no) REFERENCES users(user_no)
);

CREATE TABLE answers(
    answer_no VARCHAR(255) PRIMARY KEY,
    question_no VARCHAR(255),
    answer_created_at TIMESTAMP,
    answer_cont VARCHAR(255),
    FOREIGN KEY(question_no) REFERENCES questions(question_no)
);

INSERT INTO users (user_no, user_id, password, nickname, email, phone, privacy_agreements, marketing_agreements, created_at, profile_img_url, nationality, gender, theme, birth_date, name, self_introduction, role)
VALUES ('1', 'jhs', '1234', 'jhs95', 'jhs@example.com',
        '010-1234-4321', true, true, '2020-02-27 14:30:00', 'test', 'KOREAN',
        'MALE', 'DARK', '1995-08-24 11:00:00', '정혜성', 'ㅎㅇㅎㅇ', 'MANGER');

INSERT INTO users VALUES
        ('2', 'isy', '1234', 'isy93', 'isy@example.com',
    '010-1254-4361', true, true, '2020-02-27 12:30:00', 'test', 'KOREAN',
    'MALE', 'DARK', '1993-08-24 11:00:00', '인승엽', 'ㅂㅇㅇㅇ', 'MANGER'),
       ('3', 'ujy', '1234', 'ujy98', 'ujy@example.com',
        '010-1114-4321', true, true, '2020-02-27 17:30:00', 'test', 'KOREAN',
        'MALE', 'DARK', '1998-05-24 11:00:00', '엄진영', 'ㅎㅇㅎㅇ', 'MANGER');