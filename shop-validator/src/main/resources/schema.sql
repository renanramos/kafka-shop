create schema if not exists product;

create table product (
    id bigserial primary key auto_increment,
    identifier varchar(100) not null,
    amount int not null
);