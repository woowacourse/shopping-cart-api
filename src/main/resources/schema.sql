drop table if exists orders;

drop table if exists cart;

drop table if exists product;

drop table if exists customer;

create table customer
(
    id       bigint       not null auto_increment,
    username varchar(255) not null,
    primary key (id)
) engine=InnoDB default charset=utf8mb4;

alter table customer
    add unique key (username);

create table product
(
    id        bigint       not null auto_increment,
    name      varchar(255) not null,
    price     integer      not null,
    image_url varchar(255),
    primary key (id)
) engine=InnoDB default charset=utf8mb4;

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

create table orders
(
    id          bigint  not null auto_increment,
    customer_id bigint  not null,
    product_id  bigint  not null,
    quantity    integer not null,
    primary key (id)
) engine=InnoDB default charset=utf8mb4;

alter table orders
    add constraint fk_orders_to_customer
        foreign key (customer_id) references customer (id);

alter table orders
    add constraint fk_orders_to_product
        foreign key (product_id) references product (id);
