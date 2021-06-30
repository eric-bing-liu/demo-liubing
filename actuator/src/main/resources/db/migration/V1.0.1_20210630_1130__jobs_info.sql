create table s_job (
	id varchar(32) not null primary key,
	job_name varchar(50),
	create_time datetime
);
create table s_job_menu_rel (
	id varchar(32) not null primary key,
	menu_id varchar(32),
	job_id varchar(32)
);