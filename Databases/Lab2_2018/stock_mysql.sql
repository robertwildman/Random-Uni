--
-- Database: stock
--
CREATE DATABASE IF NOT EXISTS stock DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE stock;

DROP TABLE IF EXISTS DEMO_ORDER_ITEMS;
DROP TABLE IF EXISTS DEMO_ORDERS;
DROP TABLE IF EXISTS DEMO_CUSTOMERS;
DROP TABLE IF EXISTS DEMO_PRODUCT_INFO;

-- --------------------------------------------------------
--
-- Table structure for table demo_customers
--


CREATE TABLE  DEMO_CUSTOMERS 
   (	CUSTOMER_ID INT(3) NOT NULL, 
	CUST_FIRST_NAME VARCHAR(20) NOT NULL, 
	CUST_LAST_NAME VARCHAR(20) NOT NULL, 
	CUST_STREET_ADDRESS1 VARCHAR(60), 
	CUST_STREET_ADDRESS2 VARCHAR(60), 
	CUST_CITY VARCHAR(30), 
	CUST_STATE VARCHAR(2), 
	CUST_POSTAL_CODE VARCHAR(10), 
	CUST_EMAIL VARCHAR(30), 
	PHONE_NUMBER1 VARCHAR(25), 
	PHONE_NUMBER2 VARCHAR(25), 
	URL VARCHAR(100), 
	CREDIT_LIMIT FLOAT(9,2), 
	TAGS TEXT(4000), 
	CONSTRAINT DEMO_CUSTOMERS_PK PRIMARY KEY (CUSTOMER_ID), 
	CONSTRAINT DEMO_CUSTOMERS_UK UNIQUE (CUST_FIRST_NAME, CUST_LAST_NAME)
   ) ENGINE=InnoDB DEFAULT CHARSET=latin1;


--
-- Dumping data for table DEMO_CUSTOMERS
--

INSERT INTO DEMO_CUSTOMERS (CUSTOMER_ID,CUST_FIRST_NAME,CUST_LAST_NAME,CUST_STREET_ADDRESS1,CUST_STREET_ADDRESS2,CUST_CITY,CUST_STATE,CUST_POSTAL_CODE,CUST_EMAIL,PHONE_NUMBER1,PHONE_NUMBER2,URL,CREDIT_LIMIT,TAGS) VALUES
("1","John","Dulles","45020 Aviation Drive","","Sterling","VA","20166","john.dulles@email.com","703-555-2143","703-555-8967","http://www.johndulles.com","1000",""),
("2","William","Hartsfield","6000 North Terminal Parkway","","Atlanta","GA","30320","","404-555-3285","","","1000","REPEAT CUSTOMER"),
("3","Edward","Logan","1 Harborside Drive","","East Boston","MA","02128","","617-555-3295","","","1000","REPEAT CUSTOMER"),
("4","Frank","OHare","10000 West OHare","","Chicago","IL","60666","","773-555-7693","","","1000",""),
("5","Fiorello","LaGuardia","Hangar Center","Third Floor","Flushing","NY","11371","","212-555-3923","","","1000",""),
("6","Albert","Lambert","10701 Lambert International Blvd.","","St. Louis","MO","63145","","314-555-4022","","","1000",""),
("7","Eugene","Bradley","Schoephoester Road","","Windsor Locks","CT","06096","","860-555-1835","","","1000","REPEAT CUSTOMER");

-- --------------------------------------------------------

--
-- Table structure for table DEMO_ORDERS
--

CREATE TABLE  DEMO_ORDERS 
   (	ORDER_ID INT(3) NOT NULL, 
	CUSTOMER_ID INT(3) NOT NULL, 
	ORDER_TOTAL FLOAT(8,2), 
	ORDER_TIMESTAMP TIMESTAMP, 
	USER_NAME VARCHAR(100), 
	TAGS TEXT(4000), 
	CONSTRAINT DEMO_ORDER_PK PRIMARY KEY (ORDER_ID)
   ) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE  DEMO_ORDERS ADD CONSTRAINT DEMO_ORDERS_CUSTOMER_ID_FK FOREIGN KEY (CUSTOMER_ID)
	  REFERENCES  DEMO_CUSTOMERS (CUSTOMER_ID) ON DELETE CASCADE;

--
-- Dumping data for table DEMO_ORDERS
--

INSERT INTO DEMO_ORDERS (ORDER_ID,CUSTOMER_ID,ORDER_TOTAL,ORDER_TIMESTAMP,USER_NAME,TAGS) VALUES
("1","7","1890","2016-10-16 15:22:00","DEMO",""),
("2","1","2380","2016-10-15 15:22:00","DEMO","LARGE ORDER"),
("3","2","1640","2016-10-26 15:22:00","DEMO",""),
("4","5","1090","2016-10-28 15:22:00","DEMO",""),
("5","6","950","2016-11-07 15:22:00","DEMO",""),
("6","3","1515","2016-11-12 15:22:00","DEMO",""),
("7","3","905","2016-11-17 15:22:00","DEMO",""),
("8","4","1060","2016-11-25 15:22:00","DEMO",""),
("9","2","730","2016-12-01 15:22:00","DEMO",""),
("10","7","870","2016-12-04 15:22:00","DEMO","");

-- --------------------------------------------------------
--
-- Table structure for DEMO_PRODUCT_INFO
--


CREATE TABLE  DEMO_PRODUCT_INFO 
   (	PRODUCT_ID INT(3) NOT NULL, 
	PRODUCT_NAME VARCHAR(50), 
	PRODUCT_DESCRIPTION TEXT(2000), 
	CATEGORY VARCHAR(30), 
	PRODUCT_AVAIL VARCHAR(1), 
	LIST_PRICE FLOAT(8,2), 
	PRODUCT_IMAGE BLOB, 
	MIMETYPE VARCHAR(255), 
	FILENAME TEXT(400), 
	IMAGE_LAST_UPDATE TIMESTAMP, 
	TAGS TEXT(4000), 
	CONSTRAINT DEMO_PRODUCT_INFO_PK PRIMARY KEY (PRODUCT_ID), 
	CONSTRAINT DEMO_PRODUCT_INFO_UK UNIQUE (PRODUCT_NAME)
   ) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table DEMO_PRODUCT_INFO
--

INSERT INTO DEMO_PRODUCT_INFO (PRODUCT_ID,PRODUCT_NAME,PRODUCT_DESCRIPTION,CATEGORY,PRODUCT_AVAIL,LIST_PRICE,MIMETYPE,FILENAME,IMAGE_LAST_UPDATE,TAGS) VALUES
("9","Mens Shoes","Leather upper and lower lace up shoes","Mens","Y","110","image/jpeg","shoes.jpg","2016-12-05 15:22:00",""),
("10","Wallet","Travel wallet suitable for men and women. Several compartments for credit cards, passports and cash","Accessories","Y","50","image/jpeg","wallet.jpg","2016-12-05 15:22:00",""),
("1","Business Shirt","Wrinkle-free cotton business shirt","Mens","Y","50","image/jpeg","shirt.jpg","2016-12-05 15:22:00","TOP SELLER"),
("2","Trousers","Black trousers suitable for every business man","Mens","Y","80","image/jpeg","pants.jpg","2016-12-05 15:22:00","TOP SELLER"),
("3","Jacket","Fully lined jacket which is both professional and extremely comfortable to wear","Mens","Y","150","image/jpeg","jacket.jpg","2016-12-05 15:22:00",""),
("4","Blouse","Silk blouse ideal for all business women","Womens","Y","60","image/jpeg","blouse.jpg","2016-12-05 15:22:00",""),
("5","Skirt","Wrinkle free skirt","Womens","Y","80","image/jpeg","skirt.jpg","05-DEC-16 03.22.00.224681 PM",""),
("6","Ladies Shoes","Low heel and cushioned interior for comfort and style in simple yet elegant shoes","Womens","Y","120","image/jpeg","heels.jpg","2016-12-05 15:22:00",""),
("7","Belt","Leather belt","Accessories","Y","30","image/jpeg","belt.jpg","05-DEC-16 03.22.00.227366 PM",""),
("8","Bag","Unisex bag suitable for carrying laptops with room for many additional items","Accessories","Y","125","image/jpeg","bag.jpg","2016-12-05 15:22:00","");

-- --------------------------------------------------------
-- Table structure for DEMO_ORDER_ITEMS
--


CREATE TABLE  DEMO_ORDER_ITEMS 
   (	ORDER_ITEM_ID INT(3) NOT NULL, 
	ORDER_ID INT(3) NOT NULL, 
	PRODUCT_ID INT(3) NOT NULL, 
	UNIT_PRICE FLOAT(8,2) NOT NULL, 
	QUANTITY INT(8) NOT NULL, 
	CONSTRAINT DEMO_ORDER_ITEMS_PK PRIMARY KEY (ORDER_ITEM_ID), 
	CONSTRAINT DEMO_ORDER_ITEMS_UK UNIQUE (ORDER_ID, PRODUCT_ID)
   ) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE  DEMO_ORDER_ITEMS ADD CONSTRAINT DEMO_ORDER_ITEMS_FK FOREIGN KEY (ORDER_ID)
	  REFERENCES  DEMO_ORDERS (ORDER_ID) ON DELETE CASCADE;

ALTER TABLE  DEMO_ORDER_ITEMS ADD CONSTRAINT DEMO_ORDER_ITEMS_PRODUCT_ID_FK FOREIGN KEY (PRODUCT_ID)
	  REFERENCES  DEMO_PRODUCT_INFO (PRODUCT_ID) ON DELETE CASCADE;

--
-- Dumping data for table DEMO_ORDER_ITEMS
--

INSERT INTO DEMO_ORDER_ITEMS (ORDER_ITEM_ID,ORDER_ID,PRODUCT_ID,UNIT_PRICE,QUANTITY) VALUES
("100","1","1","50","10"),
("101","1","2","80","8"),
("102","1","3","150","5"),
("103","2","1","50","3"),
("104","2","2","80","3"),
("105","2","3","150","3"),
("106","2","4","60","3"),
("107","2","5","80","3"),
("108","2","6","120","2"),
("109","2","7","30","2"),
("110","2","8","125","4"),
("111","2","9","110","2"),
("112","2","10","50","2"),
("113","3","4","60","4"),
("114","3","5","80","4"),
("115","3","6","120","4"),
("116","3","8","125","4"),
("117","3","10","50","2"),
("118","4","6","120","2"),
("119","4","7","30","6"),
("120","4","8","125","2"),
("121","4","9","110","2"),
("122","4","10","50","4"),
("123","5","1","50","3"),
("124","5","2","80","2"),
("125","5","3","150","2"),
("126","5","4","60","3"),
("127","5","5","80","2"),
("128","6","3","150","3"),
("129","6","6","120","3"),
("130","6","8","125","3"),
("131","6","9","110","3"),
("132","7","1","50","2"),
("133","7","2","80","2"),
("134","7","4","60","2"),
("135","7","5","80","2"),
("136","7","7","30","3"),
("137","7","8","125","1"),
("138","7","10","50","3"),
("139","8","2","80","2"),
("140","8","3","150","3"),
("141","8","6","120","1"),
("142","8","9","110","3"),
("143","9","4","60","4"),
("144","9","5","80","3"),
("145","9","8","125","2"),
("146","10","1","50","5"),
("147","10","2","80","4"),
("148","10","3","150","2");

