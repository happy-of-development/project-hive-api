-- TB_USER
insert into tb_user (id, name, password, del_yn, mobile, photo, created_id, created_date, modified_id, modified_date)
values  ('0', '관리자', 'password', 'N', '01094971093', null, '0', '2023-04-09 15:42:57', '0', '2023-04-09 15:43:11'),
        ('1', '백봉준', 'password', 'N', '01094971093', null, '0', '2023-04-09 15:43:00', '0', '2023-04-09 15:43:14');

-- TB_TAEM
insert into tb_team (id, name, created_id, created_date, modified_id, modified_date)
values  (0, '개발1팀', '0', '2023-04-09 15:34:02', '0', '2023-04-09 15:34:09');

-- TB_TEAM_USER
insert into tb_team_user (team_id, user_id, created_id, created_date, modified_id, modified_date)
values  (0, '1', '0', '2023-04-09 17:57:28', '0', '2023-04-09 17:57:33');

-- TB_PROJECT
insert into tb_project (id, name, begin_date, end_date, status, created_id, created_date, modified_id, modified_date)
values  (0, '프로젝트 하이브', '2023-04-09 17:34:42', '2023-04-09 17:34:46', 'INIT', '0', '2023-04-09 17:54:25', '0', '2023-04-09 17:54:31');

-- TB_PROJECT_USER
insert into tb_project_user (project_id, user_id, role, created_id, created_date, modified_id, modified_date)
values  (0, '1', null, '0', '2023-04-09 17:56:21', '0', '2023-04-09 17:56:23');

-- TB_PROJECT_MM
insert into tb_project_mm (project_id, user_id, project_year, type, m1, m2, m3, m4, m5, m6, m7, m8, m9, m10, m11, m12, created_id, created_date, modified_id, modified_date)
values  (0, '1', '2023', 'ACTUAL', 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, '0', '2023-04-09 18:17:21', '0', '2023-04-09 18:17:23'),
        (0, '1', '2023', 'EXPECT', 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, '0', '2023-04-09 18:17:21', '0', '2023-04-09 18:17:23');