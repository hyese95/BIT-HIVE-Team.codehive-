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
    user_no INT AUTO_INCREMENT PRIMARY KEY,
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
    gender VARCHAR(10) NOT NULL,
    theme VARCHAR(10) NOT NULL DEFAULT 'DARK',
    birth_date DATE NOT NULL,
    name VARCHAR(30) NOT NULL,
    self_introduction VARCHAR(150),
    role VARCHAR(30) NOT NULL DEFAULT 'USER'
);

CREATE TABLE notification_settings(
    user_no INT AUTO_INCREMENT PRIMARY KEY,
    volatility_yn BOOLEAN DEFAULT false,
    portfolio_yn BOOLEAN DEFAULT false,
    target_price_yn	BOOLEAN DEFAULT false,
    trade_yn BOOLEAN DEFAULT false,
    like_yn	BOOLEAN DEFAULT false,
    comment_yn BOOLEAN DEFAULT false,
    reply_yn BOOLEAN DEFAULT false,
    follower_yn	BOOLEAN DEFAULT false
);

CREATE TABLE target_price_alerts(
    target_price_alerts_no INT AUTO_INCREMENT PRIMARY KEY,
    user_no INT NOT NULL,
    market VARCHAR(255) NOT NULL,
    target_price DOUBLE NOT NULL,
    FOREIGN KEY(user_no) REFERENCES users(user_no)
);

CREATE TABLE volatility_alerts(
    volatility_alerts_no  INT AUTO_INCREMENT PRIMARY KEY,
    user_no INT NOT NULL,
    market VARCHAR(255) NOT NULL,
    FOREIGN KEY(user_no) REFERENCES users(user_no)
);

CREATE TABLE coin_transactions(
    trans_no INT AUTO_INCREMENT PRIMARY KEY,
    user_no INT NOT NULL,
    market VARCHAR(255) NOT NULL,
    transaction_type VARCHAR(20) NOT NULL,
    price DOUBLE NOT NULL,
    transaction_cnt DOUBLE NOT NULL,
    transaction_date TIMESTAMP NOT NULL,
    transaction_state VARCHAR(20),
    FOREIGN KEY(user_no) REFERENCES users(user_no)
);

CREATE TABLE favorite_markets_folders(
    list_no	INT AUTO_INCREMENT PRIMARY KEY,
    user_no	INT NOT NULL,
    list_name VARCHAR(255),
    FOREIGN KEY(user_no) REFERENCES users(user_no)
);

CREATE TABLE favorite_markets(
    favorite_coin_no INT AUTO_INCREMENT PRIMARY KEY,
    user_no INT NOT NULL,
    market VARCHAR(255) NOT NULL,
    list_no INT NOT NULL,
    sort_order VARCHAR(10),
    FOREIGN KEY(user_no) REFERENCES users(user_no),
    FOREIGN KEY(list_no) REFERENCES favorite_markets_folders(list_no)
);

CREATE TABLE follows(
    follower_user_no  INT NOT NULL,
    following_user_no INT NOT NULL,
    following_date    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY(follower_user_no, following_user_no),
    CONSTRAINT fk_follows_follower FOREIGN KEY(follower_user_no) REFERENCES users(user_no) ON DELETE CASCADE,
    CONSTRAINT fk_follows_following FOREIGN KEY(following_user_no) REFERENCES users(user_no) ON DELETE CASCADE
);

CREATE TABLE posts(
    post_no INT AUTO_INCREMENT PRIMARY KEY,
    user_no INT NOT NULL,
    post_cont TEXT,
    post_created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    img_url VARCHAR(255),
    category VARCHAR(20),
    FOREIGN KEY (user_no) REFERENCES users(user_no)
);

CREATE TABLE comments(
    comment_no INT AUTO_INCREMENT PRIMARY KEY,
    post_no INT NOT NULL,
    user_no INT NOT NULL,
    comment_created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    parent_no INT,
    comment_cont VARCHAR(255) NOT NULL,
    FOREIGN KEY(post_no) REFERENCES posts(post_no),
    FOREIGN KEY(user_no) REFERENCES users(user_no),
    FOREIGN KEY(parent_no) REFERENCES comments(comment_no)
);

CREATE TABLE chatting_logs(
    chat_no INT AUTO_INCREMENT PRIMARY KEY,
    user_no INT NOT NULL,
    chat_created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    content VARCHAR(255) NOT NULL,
    FOREIGN KEY(user_no) REFERENCES users(user_no)
);

CREATE TABLE post_likes(
    user_no INT NOT NULL,
    post_no INT NOT NULL,
    PRIMARY KEY(user_no, post_no),
    like_type BOOLEAN,
    FOREIGN KEY(post_no) REFERENCES posts(post_no),
    FOREIGN KEY(user_no) REFERENCES users(user_no)
);

CREATE TABLE post_bookmarks(
    user_no INT NOT NULL,
    post_no INT NOT NULL,
    PRIMARY KEY(user_no, post_no),
    FOREIGN KEY(post_no) REFERENCES posts(post_no),
    FOREIGN KEY(user_no) REFERENCES users(user_no)
);

CREATE TABLE comment_likes(
    user_no INT,
    comment_no INT,
    PRIMARY KEY(user_no, comment_no),
    like_type BOOLEAN,
    FOREIGN KEY(user_no) REFERENCES users(user_no),
    FOREIGN KEY(comment_no) REFERENCES comments(comment_no)
);

CREATE TABLE password_change_logs(
    password_log_no INT AUTO_INCREMENT PRIMARY KEY,
    user_no INT NOT NULL,
    changed_password VARCHAR(50),
    change_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    FOREIGN KEY(user_no) REFERENCES users(user_no)
);

CREATE TABLE login_logs(
    login_log_no INT AUTO_INCREMENT PRIMARY KEY,
    user_no INT NOT NULL,
    login_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    FOREIGN KEY(user_no) REFERENCES users(user_no)
);

CREATE TABLE exp_activity(
    activity_type VARCHAR(100) PRIMARY KEY,
    exp_amount INT,
    description VARCHAR(255)
);

CREATE TABLE exp_logs(
    exp_log_no INT AUTO_INCREMENT PRIMARY KEY,
    user_no INT NOT NULL,
    activity_type VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    FOREIGN KEY(user_no) REFERENCES users(user_no),
    FOREIGN KEY(activity_type) REFERENCES exp_activity(activity_type)
);

CREATE TABLE level_exp(
    level INT PRIMARY KEY,
    required_exp INT
);

CREATE TABLE guides(
    guide_no INT AUTO_INCREMENT PRIMARY KEY,
    guide_cont TEXT,
    guide_created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    guide_title VARCHAR(255)
);

CREATE TABLE questions(
    question_no INT AUTO_INCREMENT PRIMARY KEY,
    user_no INT NOT NULL,
    question_cont TEXT,
    question_created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    question_title VARCHAR(255),
    question_status VARCHAR(20),
    question_option VARCHAR(20),
    FOREIGN KEY(user_no) REFERENCES users(user_no)
);

CREATE TABLE answers(
    answer_no INT AUTO_INCREMENT PRIMARY KEY,
    question_no INT NOT NULL,
    answer_created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    answer_cont TEXT,
    FOREIGN KEY(question_no) REFERENCES questions(question_no)
);

