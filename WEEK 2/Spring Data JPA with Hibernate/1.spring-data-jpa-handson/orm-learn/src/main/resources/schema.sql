-- Country table (Hands on 5 / Country table creation)
drop table if exists country;

create table country (
    co_code varchar(2) primary key,
    co_name varchar(50)
);

-- Employee table (Hands on 4 - JPA vs Hibernate vs Spring Data JPA comparison)
drop table if exists employee;

create table employee (
    id int auto_increment primary key,
    first_name varchar(50),
    last_name varchar(50),
    salary int
);
