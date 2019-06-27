--
-- PostgreSQL database
--

CREATE TABLE USERS (
id bigserial NOT NULL,
password VARCHAR(100) NOT NULL,
first_name VARCHAR(30) NOT NULL,
last_name VARCHAR(30) NOT NULL,
email VARCHAR(30) NOT NULL, 
CONSTRAINT "PK_USER" PRIMARY KEY (id),
UNIQUE (email)
);

INSERT INTO users(password, first_name, last_name, email)
VALUES ('admin_001', 'AdminName','AdminLastName','admin01@xyz.com');

create table PROFILE(
id bigserial NOT NULL,
type VARCHAR(30) NOT NULL,
CONSTRAINT "PK_PROFILE" PRIMARY KEY (id),
UNIQUE (type)
);

INSERT INTO PROFILE(type)
VALUES ('USER');

INSERT INTO PROFILE(type)
VALUES ('ADMIN');

INSERT INTO PROFILE(type)
VALUES ('DBA');

CREATE TABLE USER_PROFILE (
user_id bigint NOT NULL,
user_profile_id bigint NOT NULL,
CONSTRAINT "PK_USER_PROFILE" PRIMARY KEY (user_id, user_profile_id),                
CONSTRAINT "FK_USER" FOREIGN KEY (user_id) REFERENCES users (id),
CONSTRAINT "FK_PROFILE" FOREIGN KEY (user_profile_id) REFERENCES profile (id)
);

INSERT INTO USER_PROFILE (user_id, user_profile_id)
SELECT u.id, p.id FROM users u, profile p
where u.email='admin01@xyz.com' and p.type='ADMIN';

CREATE TABLE CUSTOMER (
customer_id bigserial NOT NULL,
name varchar(30) NOT NULL,
address varchar(100) NOT NULL,
postal_code bigint NOT NULL,
contact_person varchar(30) NOT NULL,
position varchar(30) NOT NULL,
phone varchar(15) NOT NULL,
email varchar(50) NOT NULL,
last_contact date DEFAULT NULL,
next_contact date DEFAULT NULL,
lead_status varchar(20) NOT NULL,
notes varchar(250) DEFAULT NULL, 
PRIMARY KEY (customer_id, email)
);

