create table SPRING_SESSION
(
    SESSION_ID            char(36)     not null
        primary key,
    CREATION_TIME         bigint       not null,
    LAST_ACCESS_TIME      bigint       not null,
    MAX_INACTIVE_INTERVAL int          not null,
    PRINCIPAL_NAME        varchar(100) null
);

create index SPRING_SESSION_IX1
    on SPRING_SESSION (LAST_ACCESS_TIME);

create table SPRING_SESSION_ATTRIBUTES
(
    SESSION_ID      char(36)     not null,
    ATTRIBUTE_NAME  varchar(200) not null,
    ATTRIBUTE_BYTES blob         not null,
    primary key (SESSION_ID, ATTRIBUTE_NAME),
    constraint SPRING_SESSION_ATTRIBUTES_FK
        foreign key (SESSION_ID) references SPRING_SESSION (SESSION_ID)
            on delete cascade
);

create index SPRING_SESSION_ATTRIBUTES_IX1
    on SPRING_SESSION_ATTRIBUTES (SESSION_ID);


create table member
(
    member_id            int auto_increment primary key,
    user_id     varchar(50)                not null,
    password      varchar(128)               not null,
    username      varchar(50)                not null,
    nickname      varchar(16)                null,
    mobile_number varchar(16)                not null,
    role          varchar(16) default 'NONE' null,
    is_deleted    tinyint(1)                 null,
    created_date  datetime                   null,
    modified_date datetime                   null,
    constraint member_user_id_uindex
        unique (user_id)
);

create table fees
(
    fees_id            bigint auto_increment primary key,
    member_id     bigint          not null,
    fee           int                  not null,
    fee_year      varchar(4)           not null,
    fee_month     varchar(2)           null,
    is_deleted    tinyint(1) default 0 null,
    created_date  datetime             null,
    modified_date datetime             null
);

create table meeting_participant
(
    meeting_participant_id   bigint   auto_increment    primary key,
    meeting_id    bigint       null,
    participant   varchar(255) null,
    created_date  datetime     null,
    modified_date datetime     null
);

create table board
(
    board_id            int auto_increment primary key,
    member_id     bigint   not null,
    board_type    varchar(10)   not null,
    title         varchar(100)  not null,
    content       text          null,
    is_deleted    int default 0 not null,
    created_date  datetime      null,
    modified_date datetime      null
);

create table file
(
    file_id               int auto_increment primary key,
    board_id         int           not null,
    file_url         varchar(200)  not null,
    file_name        varchar(100)  not null,
    origin_file_name varchar(100)  not null,
    file_type        varchar(64)   ,
    file_size        int           not null,
    is_deleted       int default 0 not null,
    created_date     datetime      null,
    modified_date    datetime      null
);

create table meeting
(
    meeting_id            bigint auto_increment primary key,
    member_id     bigint                   null,
    type          varchar(16) default 'REGULAR' null,
    meeting_order int                           null,
    title         varchar(100)                  not null,
    content       text                          null,
    url           varchar(100)                  null,
    place         varchar(64)                   not null,
    is_deleted    int                           null,
    created_date  datetime                      null,
    modified_date datetime                      null
);

create table posts
(
    id            bigint auto_increment
        primary key,
    title         varchar(500) not null,
    content       text         not null,
    author        varchar(255) null,
    created_date  datetime     null,
    modified_date datetime     null
);
