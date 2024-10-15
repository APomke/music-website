create table collect
(
    id           varchar(255) not null,
    uid          varchar(255) not null,
    title        varchar(255) not null,
    music_artist varchar(255) null,
    url          varchar(255) null,
    icon_url     varchar(255) null,
    level        varchar(255) null,
    constraint collect_pk
        unique (id, title)
);

create table music
(
    id              varchar(255)             null,
    title           varchar(255)             null,
    artist          varchar(255)             null,
    url             varchar(255)             null,
    icon_url        varchar(255)             null,
    lyric_url       varchar(255)             null,
    file_size       varchar(255)             null,
    level           varchar(255)             null,
    playing         tinyint(1)   default 0   null,
    collect_count   varchar(255) default '0' null,
    upload_user_uid varchar(255)             null
);

create table user
(
    uid        varchar(255) null,
    username   varchar(255) null,
    password   varchar(255) null,
    email      varchar(255) null,
    sex        varchar(255) null,
    age        varchar(255) null,
    avatar_url varchar(255) null
);

INSERT INTO music.music (id, title, artist, url, icon_url, lyric_url, file_size, level, playing, collect_count, upload_user_uid) VALUES ('1', '稻香', '周杰伦', 'https://music-520.oss-cn-hangzhou.aliyuncs.com/%E7%A8%BB%E9%A6%99/11.%E7%A8%BB%E9%A6%99.wav', 'https://awsimage-1.oss-cn-hangzhou.aliyuncs.com/image-20240920091824796.png', null, null, 'SQ', 0, '0', null);
INSERT INTO music.music (id, title, artist, url, icon_url, lyric_url, file_size, level, playing, collect_count, upload_user_uid) VALUES ('2', '爱在西元前', '周杰伦', 'https://music-520.oss-cn-hangzhou.aliyuncs.com/%E7%88%B1%E5%9C%A8%E8%A5%BF%E5%85%83%E5%89%8D/01.%E7%88%B1%E5%9C%A8%E8%A5%BF%E5%85%83%E5%89%8D.wav', 'https://awsimage-1.oss-cn-hangzhou.aliyuncs.com/OIP.5yLhokSv4a8iLOWHvHFnpgHaEo.jpeg', null, null, 'SQ', 0, '0', null);
INSERT INTO music.music (id, title, artist, url, icon_url, lyric_url, file_size, level, playing, collect_count, upload_user_uid) VALUES ('1846095239165616130', '兰亭序', '周杰伦', 'https://music-520.oss-cn-hangzhou.aliyuncs.com/兰亭序/07.兰亭序.wav', 'https://music-520.oss-cn-hangzhou.aliyuncs.com/兰亭序/兰亭序.jpg', null, '42.723262786865234', null, 0, '0', null);


INSERT INTO music.user (uid, username, password, email, sex, age, avatar_url) VALUES ('xx-yy-zz', 'admin', '88888888', 'example@email.com', 'male', '25', 'https://awsimage-1.oss-cn-hangzhou.aliyuncs.com/image-20240926155052371.png');

INSERT INTO music.collect (id, uid, title, music_artist, url, icon_url, level) VALUES ('c0e05552953a44689abc87e880e7887f', 'xx-yy-zz', '稻香', '周杰伦', 'https://music-520.oss-cn-hangzhou.aliyuncs.com/%E7%A8%BB%E9%A6%99/11.%E7%A8%BB%E9%A6%99.wav', 'https://awsimage-1.oss-cn-hangzhou.aliyuncs.com/image-20240920091824796.png', 'SQ');


