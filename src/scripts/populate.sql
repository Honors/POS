CREATE TABLE INVENTORY
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
)

--i have yet to make this, more for construction logic
CREATE TABLE RETURNS
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
)