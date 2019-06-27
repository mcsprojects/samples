--
-- MySQL database
--

create table user_mgmt (
id BIGINT NOT NULL AUTO_INCREMENT,
name VARCHAR(50) NOT NULL,
username VARCHAR(50) NOT NULL,
password VARCHAR(100) NOT NULL,
PRIMARY KEY (id)
);

create table user_mgmt_roles (
user_mgmt_id BIGINT(5) NOT NULL,
roles VARCHAR(50) NOT NULL
);

insert into user_mgmt(name,username,password) values ('Admin01','admin','admin01');
insert into user_mgmt(name,username,password) values ('User01','user01','user01');

insert into user_mgmt_roles(user_mgmt_id,roles) values (1,'ADMIN');
insert into user_mgmt_roles(user_mgmt_id,roles) values (1,'USER');
insert into user_mgmt_roles(user_mgmt_id,roles) values (2,'USER');

create table employee_mgmt(
id BIGINT NOT NULL AUTO_INCREMENT, 
employee VARCHAR(50) NOT NULL,
`address` varchar(100) NOT NULL,
phone varchar(15) NOT NULL,
`email` varchar(50) DEFAULT NULL,
dni VARCHAR(30) NOT NULL,
birth_date date DEFAULT NULL,
deparment varchar(50) DEFAULT NULL,
job_title varchar(50) DEFAULT NULL,
salary DOUBLE NOT NULL, 
contract varchar(50) DEFAULT NULL,
start_date date DEFAULT NULL,
end_date date DEFAULT NULL,
PRIMARY KEY (id)
);
