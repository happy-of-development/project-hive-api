create table tb_project
(
    id            int         not null comment '프로젝트 아이디'
        primary key,
    name          varchar(50) not null comment '프로젝트 이름',
    begin_date    datetime    not null comment '시작 날짜',
    end_date      datetime    not null comment '종료 날짜',
    status        varchar(10) not null comment '프로젝트 상태',
    created_id    varchar(8)  not null comment '생성 아이디',
    created_date  datetime    not null comment '생성 날짜',
    modified_id   varchar(8)  null comment '수정 아이디',
    modified_date datetime    null comment '수정 날짜'
);

create table tb_team
(
    id            int           not null comment '팀 아이디'
        primary key,
    name          varchar(50)   not null comment '팀 이름',
    description   varchar(200)  null comment '팀 설명',
    created_id    varchar(8)    not null comment '생성 아이디',
    created_date  datetime      not null comment '생성 날짜',
    modified_id   varchar(8)    null comment '수정 아이디',
    modified_date datetime      null comment '수정 날짜'
);

create table tb_user
(
    id            varchar(8)  not null comment '사용자 아이디'
        primary key,
    name          varchar(15) not null comment '사용자 이름',
    password      varchar(25) not null comment '사용자 패스워드',
    del_yn        varchar(1)  not null comment '삭제 여부 Y, N',
    email         varchar(50) null comment '이메일',
    mobile        varchar(11) null comment '휴대폰 번호',
    photo         blob        null comment '사진',
    created_id    varchar(8)  not null comment '생성 아이디',
    created_date  datetime    not null comment '생성 날짜',
    modified_id   varchar(8)  null comment '수정 아이디',
    modified_date datetime    null comment '수정 날짜'
);

create table tb_project_mm
(
    project_id    int         not null comment '프로젝트 아이디',
    user_id       varchar(8)  not null comment '사용자 아이디',
    project_year  varchar(4)  not null comment '년도',
    type          varchar(10) not null comment '종류 (EXPECT, ACTUAL, REQ_EXPECT, REQ_ACTUAL)',
    m1            float       null comment '1월',
    m2            float       null comment '2월',
    m3            float       null comment '3월',
    m4            float       null comment '4월',
    m5            float       null comment '5월',
    m6            float       null comment '6월',
    m7            float       null comment '7월',
    m8            float       null comment '8월',
    m9            float       null comment '9월',
    m10           float       null comment '10월',
    m11           float       null comment '11월',
    m12           float       null comment '12월',
    created_id    varchar(8)  not null comment '생성 아이디',
    created_date  datetime    not null comment '생성 날짜',
    modified_id   varchar(8)  null comment '수정 아이디',
    modified_date datetime    null comment '수정 날짜',
    primary key (project_id, user_id, project_year, type),
    constraint tb_project_mm_pk
        unique (project_id, user_id, project_year, type),
    constraint tb_project_mm_tb_project_id_fk
        foreign key (project_id) references tb_project (id),
    constraint tb_project_mm_tb_user_id_fk
        foreign key (user_id) references tb_user (id)
)
    comment '프로젝트 MM 테이블';

create table tb_project_user
(
    project_id    int         not null comment '프로젝트 아이디',
    user_id       varchar(8)  not null comment '사용자 아이디',
    role          varchar(10) null comment '역할 (PM, DM)',
    created_id    varchar(8)  not null comment '생성 아이디',
    created_date  datetime    not null comment '생성 날짜',
    modified_id   varchar(8)  null comment '수정 아이디',
    modified_date datetime    null comment '수정 날짜',
    primary key (project_id, user_id),
    constraint tb_project_user_tb_project_id_fk
        foreign key (project_id) references tb_project (id),
    constraint tb_project_user_tb_user_id_fk
        foreign key (user_id) references tb_user (id)
)
    comment '프로젝트-사용자 연관 테이블';

create table tb_system_history
(
    id       varchar(36) not null comment '히스토리 아이디'
        primary key,
    user_id  varchar(8)  not null,
    date     datetime    not null comment '히스토리 날짜',
    action   varchar(10) not null comment '히스토리 활동',
    category varchar(10) not null comment '히스토리 분류',
    data     text        null comment '히스토리 데이터',
    constraint tb_system_history_tb_user_id_fk
        foreign key (user_id) references tb_user (id)
)
    comment '시스템 기록 테이블';

create table tb_team_user
(
    team_id       int        not null comment '팀 아이디',
    user_id       varchar(8) not null comment '사용자 아이디',
    created_id    varchar(8) not null comment '생성 아이디',
    created_date  datetime   not null comment '생성 날짜',
    modified_id   varchar(8) null comment '수정 아이디',
    modified_date datetime   null comment '수정 날짜',
    primary key (team_id, user_id),
    constraint tb_team_user_tb_team_id_fk
        foreign key (team_id) references tb_team (id),
    constraint tb_team_user_tb_user_id_fk
        foreign key (user_id) references tb_user (id)
);


-- TB_USER
insert into tb_user (id, name, password, del_yn, email, mobile, photo, created_id, created_date, modified_id, modified_date)
values  ('admin', '관리자', 'password', 'N', 'admin@gamil.com', '01094971093', null, 'admin', '2023-04-09 15:42:57', 'admin', '2023-04-09 15:43:11'),
        ('bongjun', '백봉준', 'password', 'N', 'bongjun.baek@gmail.com', '01094971093', null, 'admin', '2023-04-09 15:43:00', 'admin', '2023-04-09 15:43:14'),
        ('11111111', '1번사용자', 'abcdef1234', 'N', 'hod@gmail.com', '01010002000', null, '10000000', NOW(), '10000000', NOW()),
        ('22222222', '2번사용자', 'abcdef1234', 'N', 'hod@gmail.com', '01010002000', null, '10000000', NOW(), '10000000', NOW()),
        ('10000000', 'admin', 'abcdef1234', 'N', 'admin@gmail.com', '01010002000', null, '10000000', NOW(), '10000000', NOW());
-- TB_TAEM
insert into tb_team (id, name, description, created_id, created_date, modified_id, modified_date)
values  (0, '개발1팀', '우리에겐 실패란 없다!', 'admin', '2023-04-09 15:34:02', 'admin', '2023-04-09 15:34:09'),
        (1, '개발팀', '개발팀 입니다.', '10000000', NOW(), '10000000', NOW());

-- TB_TEAM_USER
insert into tb_team_user (team_id, user_id, created_id, created_date, modified_id, modified_date)
values  (0, 'bongjun', 'admin', '2023-04-09 17:57:28', 'admin', '2023-04-09 17:57:33'),
        (1, '11111111', '10000000', NOW(), '10000000', NOW()),
        (1, '22222222', '10000000', NOW(), '10000000', NOW());

-- TB_PROJECT
insert into tb_project (id, name, begin_date, end_date, status, created_id, created_date, modified_id, modified_date)
values  (0, '프로젝트 하이브', '2023-01-01 00:00:00', '2023-12-31 00:00:00', 'INIT', 'admin', '2023-04-09 00:00:00', 'admin', '2023-04-09 17:54:31'),
        (1, '첫프로젝트', '2023-01-01 00:00:00', '2023-12-31 00:00:00', 'INIT', '10000000', NOW(), '10000000', NOW());

-- TB_PROJECT_USER
insert into tb_project_user (project_id, user_id, role, created_id, created_date, modified_id, modified_date)
values  (0, 'bongjun', null, 'admin', '2023-04-09 17:56:21', 'admin', '2023-04-09 17:56:23'),
        (1, '11111111', 'PM', '10000000', NOW(), '10000000', NOW());

-- TB_PROJECT_MM
insert into tb_project_mm (project_id, user_id, project_year, type, m1, m2, m3, m4, m5, m6, m7, m8, m9, m10, m11, m12, created_id, created_date, modified_id, modified_date)
values  (0, 'bongjun', '2023', 'ACTUAL', 1, 1, 1, 1, 1, 1, 0, 0, null, null, null, null, 'admin', '2023-04-09 18:17:21', 'admin', '2023-04-09 18:17:23'),
        (0, 'bongjun', '2023', 'EXPECT', 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 'admin', '2023-04-09 18:17:21', 'admin', '2023-04-09 18:17:23'),
        (1, '11111111', '2023', 'EXPECT', 0.1, 0.2, 0.3, 0.4, 0.1, 0.5, 0.5, 0.5, 0.1, 0.2, 0.3, 0.4, '10000000', NOW(), '10000000', NOW()),
        (1, '11111111', '2023', 'ACTUAL', 0.4, 0.1, 0.5, 0.5, 0.5, 0.1, 0.2, 0.3, 0.1, 0.2, 0.3, 0.4, '10000000', NOW(), '10000000', NOW()),
        (1, '22222222', '2023', 'EXPECT', 0.2, 0.2, 0.2, 0.2, 0.2, 0.2, 0.2, 0.2, 0.2, 0.2, 0.2, 0.2, '10000000', NOW(), '10000000', NOW()),
        (1, '22222222', '2023', 'ACTUAL', 0.4, 0.4, 0.4, 0.4, 0.4, 0.4, 0.4, 0.4, 0.4, 0.4, 0.4, 0.4, '10000000', NOW(), '10000000', NOW());
