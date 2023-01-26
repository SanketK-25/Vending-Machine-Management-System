create or replace type product_arr as varray(20) of varchar(10);

create or replace type address as object(
    street varchar(30),
    city varchar(30),
    pincode varchar(10)
);

create table vending_machine(
    vm_id varchar(10) not null,
    vm_address address not null,
    vm_type varchar(10) not null,
    vm_item_count numeric not null,
    vm_product_array product_arr not null,
    vm_p_min numeric not null,
    vm_p_max numeric not null,
    vm_balance numeric not null,
    check(vm_type in ('chocolate', 'snack', 'soft drink', 'frozen', 'icecream')),
    primary key(vm_id)
);
alter table vending_machine
add constraint Type_Check check(vm_type in ('chocolate', 'snack', 'soft drink', 'frozen', 'icecream'));


create table product(
    product_id varchar(10) not null,
    product_name varchar(50) not null,
    product_type varchar(20) not null,
    product_price numeric not null,
    product_image blob not null,
    check(product_type in ('chocolate', 'snack', 'soft drink', 'frozen', 'icecream')),
    primary key(product_id)
);

create table item(
    vm_id varchar(10) not null,
    product_id varchar(10) not null,
    product_count integer not null,
    product_expiry date not null,
    last_modify date not null,
    foreign key (vm_id) references vending_machine (vm_id) on delete cascade,
    foreign key (product_id) references product (product_id) on delete cascade
);

create table notifications(
    vm_id varchar(10) not null,
    product_id varchar(10),
    description varchar(20) not null,
    timestamp date not null,
    check(description in ('Product Refill', 'Coinbox Full', 'Product Expired')),
    foreign key (vm_id) references vending_machine (vm_id) on delete cascade,
    foreign key (product_id) references product (product_id) on delete cascade
);
alter table notifications 
modify description varchar(20);
create table records(
    record_id varchar(10) not null,
    vm_id varchar(10) not null,
    product_id varchar(10) not null,
    timestamp date not null,
    product_quantity numeric not null,
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
     timetamp date not null,
     foreign key (vm_id) references vending_machine (vm_id) on delete cascade,
     primary key(collection_id)
);


-- Sequence for record id
CREATE SEQUENCE RECORD_ID_SEQ START WITH 1 NOCACHE;
-- Sequence for Collection id
CREATE SEQUENCE COLLECTION_ID_SEQ START WITH 1 NOCACHE;
-- Sequence for Product id
CREATE SEQUENCE Product_ID_SEQ START WITH 82 NOCACHE;

-- setting timezone and date format
ALTER SESSION SET TIME_ZONE = '-5:30';
ALTER SESSION SET NLS_DATE_FORMAT = 'DD-MON-YYYY HH24:MI:SS';

-- Trigger function to Notify the user when prod_count is below threshold
create or replace trigger TRG_CHECK_P_MIN 
before insert or update on item
for each row
DECLARE 
target_vm_p_min numeric;
cur_date date;
BEGIN
    SELECT vending_machine.vm_p_min 
    INTO target_vm_p_min 
    FROM vending_machine 
    WHERE vending_machine.vm_id = :new.vm_id;
    
    SELECT CURRENT_DATE INTO cur_date FROM DUAL;
    
    IF :new.product_count <= target_vm_p_min THEN
        insert into notifications values( :new.vm_id, :new.product_id, 'Product Refill', cur_date);
    END IF;
END;

-- Trigger Function to add records 
create or replace trigger TRG_ADD_RECORD 
before insert or update on item
for each row
DECLARE 
target_vm_p_min numeric;
cur_date date;
prod_qty numeric;
actn varchar(10);
BEGIN
    SELECT vending_machine.vm_p_min 
    INTO target_vm_p_min 
    FROM vending_machine 
    WHERE vending_machine.vm_id = :new.vm_id;
    
    SELECT CURRENT_DATE INTO cur_date FROM DUAL;
    prod_qty := :new.product_count - :old.product_count;
    
    :new.last_modify := cur_date;
    
    IF prod_qty < 0 THEN
        actn := 'Sold';
    END IF;
    IF prod_qty > 0 THEN
        actn := 'Refilled';
    END IF;
    
    insert into records values(RECORD_ID_SEQ.NEXTVAL, :new.vm_id, :new.product_id, cur_date, prod_qty, actn);
    
END;

-- Procedure to check for coinbox 
CREATE OR REPLACE PROCEDURE COINBOX_CHECK_PROCEDURE
AS
cur_vm_id numeric;
cur_date date;
CURSOR CUR IS 
    SELECT vm_id
    FROM vending_machine
    WHERE vm_balance > 10000; 
BEGIN
    SELECT CURRENT_DATE INTO cur_date FROM DUAL;
    
    OPEN CUR;
    LOOP    
        FETCH CUR INTO cur_vm_id;
        insert into notifications values( cur_vm_id, NULL, 'Coinbox Full', cur_date);
        EXIT WHEN CUR%NOTFOUND;  
    END LOOP;
    CLOSE CUR;
END;


-- Procedure to check for expired products
CREATE OR REPLACE PROCEDURE EXPIRED_PRODUCT_CHECK_PROCEDURE
AS
cur_vm_id numeric;
cur_product_id numeric;
cur_date date;
CURSOR CUR IS 
    SELECT vm_id, product_id 
    FROM item
    WHERE product_expiry < cur_date; 
BEGIN
    SELECT CURRENT_DATE INTO cur_date FROM DUAL;
    
    OPEN CUR;
    LOOP    
        FETCH CUR INTO cur_vm_id, cur_product_id;
        insert into notifications values( cur_vm_id, cur_product_id, 'Product Expired', cur_date);
        EXIT WHEN CUR%NOTFOUND;  
    END LOOP;
    CLOSE CUR;
END;


