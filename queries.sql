create database coupondb;
use coupondb;

create table coupon(
                       id int auto_increment primary key,
                       code varchar(20) unique,
                       discount decimal(8,3),
                       exp_date varchar(100)
);

select * from coupon;

create database productdb;

use productdb;

create table product(
                        id int auto_increment primary key,
                        name varchar(20),
                        description varchar(100),
                        price decimal(8,3)
);
select * from product;