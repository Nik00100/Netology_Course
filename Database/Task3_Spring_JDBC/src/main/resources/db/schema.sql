create schema if not exists my_database;
use my_database;

create table if not exists CUSTOMERS
(
    id           int auto_increment,
    name         varchar(50) not null ,
    surname      varchar(50) not null ,
    age          int CHECK ( age > 0 AND age < 121),
    phone_number char(15)    not null ,
    PRIMARY KEY (id)
    );

create table if not exists ORDERS
(
    id           int auto_increment,
    date         timestamp    not null default now(),
    customer_id  int not null ,
    product_name varchar(255) not null ,
    amount       int CHECK ( amount > 0 ) not null ,
    PRIMARY KEY (id),
    FOREIGN KEY (customer_id) REFERENCES CUSTOMERS (id),
    index (customer_id)
    );