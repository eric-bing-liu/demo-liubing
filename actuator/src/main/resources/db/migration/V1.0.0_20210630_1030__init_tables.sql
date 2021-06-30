
create table s_user (
	id varchar(32) not null primary key,
	user_name varchar(50),
	create_time datetime
);

create table s_menu (
	id varchar(32) not null primary key,
	menu_name varchar(50),
	create_time datetime
);