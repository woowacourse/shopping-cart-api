drop table if exists product;

create table product
(
    id    bigint       not null auto_increment,
    name  varchar(255) not null,
    price integer      not null,
    primary key (id)
) engine=InnoDB default charset=utf8mb4;
