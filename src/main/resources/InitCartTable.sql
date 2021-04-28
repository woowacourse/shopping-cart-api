drop table if exists product;

create table product
(
    id    bigint       not null auto_increment,
    name  varchar(255) not null,
    price integer      not null,
    image_url varchar(255),
    primary key (id)
) engine=InnoDB default charset=utf8mb4;

drop table if exists cart;

create table cart
(
    id          bigint not null auto_increment,
    customer_id bigint not null,
    product_id  bigint not null
);

alter table cart
    add constraint fk_cart_to_customer
        foreign key (customer_id) references customer (id);

alter table cart
    add constraint fk_cart_to_product
        foreign key (product_id) references product (id);
