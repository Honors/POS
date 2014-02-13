CREATE TABLE SpiritStore.INVENTORY
(
SKU int,
UPC varchar(255),
name varchar(255),
brand varchar(255),
color varchar(255),
size varchar(255),
type varchar(255),
gender varchar(255),
client varchar(255),
date varchar(255),
notes varchar(255),
price varchar(255),
cost varchar(255),
quantity int
);

CREATE TABLE RETURN
(
SKU int,
UPC varchar(255),
name varchar(255),
brand varchar(255),
color varchar(255),
size varchar(255),
type varchar(255),
gender varchar(255),
client varchar(255),
date varchar(255),
notes varchar(255),
price varchar(255),
cost varchar(255),
quantity int,
status varchar(255)
);

CREATE TABLE SpiritStore.LOGIN
(
username varchar(255),
password varchar(255),
admin boolean
);

--elements created
CREATE TABLE SpiritStore.CLIENT
(
ID int,
element varchar(255)
);

CREATE TABLE SpiritStore.BRAND
(
ID int,
element varchar(255)
);

CREATE TABLE SpiritStore.TYPE
(
ID int,
element varchar(255)
);

CREATE TABLE SpiritStore.GENDER
(
ID int,
element varchar(255)
);

CREATE TABLE SpiritStore.COLOR
(
ID int,
element varchar(255)
);

CREATE TABLE SpiritStore.SIZE
(
ID int,
element varchar(255)
);