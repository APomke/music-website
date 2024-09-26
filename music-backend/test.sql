
-- music
create table music (
    id varchar(255),
    title varchar(255),
    artist varchar(255),
    url varchar(255),
    icon_url varchar(255),
    lyric_url varchar(255),
    file_size varchar(255),
    level varchar(255),
    playing tinyint(1);
    collect_count varchar(255)
);

-- user
create table user (
    uid varchar(255),
    username varchar(255),
    password varchar(255),
    email varchar(255),
    sex varchar(255),
    age varchar(255),
    avatar_url varchar(255)
);
INSERT INTO user (uid,username, password, email, sex, age, avatar_url)
VALUES ('xx-yy-zz', 'admin', '88888888', 'example@email.com', 'male', 25, 'http://example.com/avatar.jpg');

-- collect
create table collect (
    id varchar(255),
    uid varchar(255),
    music_title varchar(255),
    music_artist varchar(255),
    url varchar(255),
    icon_url varchar(255),
    level varchar(255)
);

ALTER TABLE music CONVERT TO CHARACTER SET utf8mb4; -- 设置数据库编码我为utf8