create or replace type product_arr as varray(20) of varchar(10);

create or replace type address as object(
    street varchar(20),
    city varchar(10),
    pincode varchar(10)
);

create table vending_machine(
    vm_id varchar(10) not null,
    vm_address address not null,
    vm_type varchar(10) not null,
    vm_item_count numeric not null,
    vm_product_array product_arr,
    vm_p_min numeric not null,
    vm_p_max numeric not null,
    vm_balance numeric not null,
    check(vm_type in ('chocolate', 'snack', 'drink', 'frozen', 'icecream')),
    primary key(vm_id)
);

insert into vending_machine values('VM1',new address('a','b','c'),'icecream',12,new prouct_arr('1','2'),2,12,0);
 
create table product(
    product_id varchar(10) not null,
    product_name varchar(20) not null,
    product_type varchar(20) not null,
    product_price numeric not null,
    product_image blob not null,
    product_expiry date not null,
    check(product_type in ('chocolate', 'snack', 'drink', 'frozen', 'icecream')),
    primary key(product_id)
);

create table item(
    vm_id varchar(10) not null,
    product_id varchar(10) not null,
    product_count integer not null,
    last_modify varchar(30) not null,
    foreign key (vm_id) references vending_machine (vm_id) on delete cascade,
    foreign key (product_id) references product (product_id) on delete cascade
);

create table notifications(
    vm_id varchar(10) not null,
    product_id varchar(10),
    description varchar(10) not null,
    timetamp varchar(30) not null,
    check(description in ('Product Refill', 'Coinbox Full', 'Product Expired')),
    foreign key (vm_id) references vending_machine (vm_id) on delete cascade,
    foreign key (product_id) references product (product_id) on delete cascade
);

create table records(
    record_id numeric not null,
    vm_id varchar(10) not null,
    product_id varchar(10) not null,
    timetamp date not null,
    product_quantity numeric ,
    action varchar(10) not null,
    check(action in ('Refilled', 'Sold')),
    foreign key (vm_id) references vending_machine (vm_id) on delete cascade,
    foreign key (product_id) references product (product_id) on delete cascade,
    primary key (record_id)
);

create table coinbox(
     collection_id varchar(10) not null,
     vm_id varchar(10) not null,
     amount numeric not null,
     timetamp varchar(30) not null,
     foreign key (vm_id) references vending_machine (vm_id) on delete cascade,
     primary key(collection_id)
);


Insert into vending_machine values ('VM3',new address('a', 'b', 'c'),'drink',12, product_arr('p2'),3,20,0);


