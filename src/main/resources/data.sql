insert into code_group(code_group_id, code_group_name,comment,reg_date,upd_date) values ('성별코드', '성별코드', '성별을 표시',now(),now());
insert into code_group(code_group_id, code_group_name,comment,reg_date,upd_date) values ('방문상태코드', '방문상태코드', '환자방문의 상태',now(),now());
insert into code_group(code_group_id, code_group_name,comment,reg_date,upd_date) values ('진료과목코드', '진료과목코드', '진료과목',now(),now());
insert into code_group(code_group_id, code_group_name,comment,reg_date,upd_date) values ('진료유형코드', '진료유형코드', '진료의 유형',now(),now());

insert into code (code_group_id,code,code_name,reg_date,upd_date) values ('성별코드','M','남',now(),now());
insert into code (code_group_id,code,code_name,reg_date,upd_date) values ('성별코드','F','여',now(),now());
insert into code (code_group_id,code,code_name,reg_date,upd_date) values ('방문상태코드','1','방문중',now(),now());
insert into code (code_group_id,code,code_name,reg_date,upd_date) values ('방문상태코드','2','종료',now(),now());
insert into code (code_group_id,code,code_name,reg_date,upd_date) values ('방문상태코드','3','취소',now(),now());
insert into code (code_group_id,code,code_name,reg_date,upd_date) values ('진료과목코드','01','내과',now(),now());
insert into code (code_group_id,code,code_name,reg_date,upd_date) values ('진료과목코드','02','안과',now(),now());
insert into code (code_group_id,code,code_name,reg_date,upd_date) values ('진료유형코드','D','약처방',now(),now());
insert into code (code_group_id,code,code_name,reg_date,upd_date) values ('진료유형코드','T','검사',now(),now());