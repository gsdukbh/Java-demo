create  table system_user(
    id int not null auto_increment,
    username varchar(50) not null,
    password varchar(50) not null,
    primary key(id)
);
insert into system_user(username, password) values('admin', 'admin');