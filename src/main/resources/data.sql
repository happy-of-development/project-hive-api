
INSERT INTO tb_user (id, name, password, del_yn, mobile, email, created_id, created_date, modified_id, modified_date)
    VALUES  ('11111111', '1번사용자', 'abcdef1234', 'N', '01010002000', 'hod@gmail.com', '10000000', NOW(), '10000000', NOW()),
            ('22222222', '2번사용자', 'abcdef1234', 'N', '01010002000', 'hod@gmail.com', '10000000', NOW(), '10000000', NOW()),
            ('10000000', 'admin', 'abcdef1234', 'N', '01010002000', 'admin@gmail.com', '10000000', NOW(), '10000000', NOW());


INSERT INTO tb_team VALUES (1, '개발팀', '개발팀 입니다.', '10000000', NOW(), '10000000', NOW());

INSERT INTO tb_team_user
    VALUES (1, '11111111', '10000000', NOW(), '10000000', NOW()),
           (1, '22222222', '10000000', NOW(), '10000000', NOW());

INSERT INTO tb_project VALUES (1, '첫프로젝트', PARSEDATETIME('2023-01-01', 'yyyy-MM-dd'), PARSEDATETIME('2023-12-31','yyyy-MM-dd'), 'INIT', '10000000', NOW(), '10000000', NOW());

INSERT INTO tb_project_user VALUES (1, '11111111', 'PM', '10000000', NOW(), '10000000', NOW());
INSERT INTO tb_project_user VALUES (1, '22222222', '', '10000000', NOW(), '10000000', NOW());

INSERT INTO tb_project_mm
    VALUES (1, '11111111', '2023', 'EXPECT', 0.1, 0.2, 0.3, 0.4, 0.1, 0.5, 0.5, 0.5, 0.1, 0.2, 0.3, 0.4, '10000000', NOW(), '10000000', NOW()),
           (1, '11111111', '2023', 'ACTUAL', 0.4, 0.1, 0.5, 0.5, 0.5, 0.1, 0.2, 0.3, 0.1, 0.2, 0.3, 0.4, '10000000', NOW(), '10000000', NOW()),
           (1, '22222222', '2023', 'EXPECT', 0.2, 0.2, 0.2, 0.2, 0.2, 0.2, 0.2, 0.2, 0.2, 0.2, 0.2, 0.2, '10000000', NOW(), '10000000', NOW()),
           (1, '22222222', '2023', 'ACTUAL', 0.4, 0.4, 0.4, 0.4, 0.4, 0.4, 0.4, 0.4, 0.4, 0.4, 0.4, 0.4, '10000000', NOW(), '10000000', NOW());