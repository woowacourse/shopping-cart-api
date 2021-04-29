drop table if exists product;

create table product
(
    id    bigint       not null auto_increment,
    name  varchar(255) not null,
    price integer      not null,
    image_url varchar(255),
    primary key (id)
);
