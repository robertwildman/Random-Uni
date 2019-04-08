--
-- Database: crm
--
CREATE DATABASE IF NOT EXISTS crm DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE crm;

DROP TABLE IF EXISTS JOB_HISTORY;
DROP TABLE IF EXISTS EMPLOYEES;
DROP TABLE IF EXISTS DEPARTMENTS;
DROP TABLE IF EXISTS LOCATIONS;
DROP TABLE IF EXISTS COUNTRIES;
DROP TABLE IF EXISTS REGIONS;
DROP TABLE IF EXISTS JOBS;
DROP TABLE IF EXISTS MESSAGES;
DROP PROCEDURE IF EXISTS LoadXML;

-- --------------------------------------------------------
--
-- Table structure for table REGIONS
--

CREATE TABLE  REGIONS 
   (	REGION_ID INT NOT NULL, 
	REGION_NAME VARCHAR(25), 
	PRIMARY KEY (REGION_ID)
   ) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------
--
-- Table structure for table COUNTRIES
--

CREATE TABLE  COUNTRIES 
   (	COUNTRY_ID CHAR(2) NOT NULL, 
	COUNTRY_NAME VARCHAR(40), 
	REGION_ID INT, 
	PRIMARY KEY (COUNTRY_ID)
   ) ENGINE=InnoDB DEFAULT CHARSET=latin1;
   
ALTER TABLE  COUNTRIES ADD CONSTRAINT COUNTR_REG_FK FOREIGN KEY (REGION_ID)
	  REFERENCES  REGIONS (REGION_ID);   

-- --------------------------------------------------------
--
-- Table structure for table JOBS
--

CREATE TABLE  JOBS 
	   (	JOB_ID VARCHAR(10), 
		JOB_TITLE VARCHAR(35) NOT NULL, 
		MIN_SALARY INT(6), 
		MAX_SALARY INT(6), 
		PRIMARY KEY (JOB_ID)
   ) ENGINE=InnoDB DEFAULT CHARSET=latin1;
   
-- --------------------------------------------------------
--
-- Table structure for table LOCATIONS
--

CREATE TABLE  LOCATIONS 
   (	LOCATION_ID INT(4), 
	STREET_ADDRESS VARCHAR(40), 
	POSTAL_CODE VARCHAR(12), 
	CITY VARCHAR(30) NOT NULL, 
	STATE_PROVINCE VARCHAR(25), 
	COUNTRY_ID CHAR(2), 
	PRIMARY KEY (LOCATION_ID)
   ) ENGINE=InnoDB DEFAULT CHARSET=latin1;
   
ALTER TABLE  LOCATIONS ADD CONSTRAINT LOC_C_ID_FK FOREIGN KEY (COUNTRY_ID)
	  REFERENCES  COUNTRIES (COUNTRY_ID);
   
-- --------------------------------------------------------
--
-- Table structure for table DEPARTMENTS
--

CREATE TABLE  DEPARTMENTS 
   (	DEPARTMENT_ID INT(4), 
	DEPARTMENT_NAME VARCHAR(30) NOT NULL, 
	MANAGER_ID INT(6), 
	LOCATION_ID INT(4), 
	PRIMARY KEY (DEPARTMENT_ID)
   ) ENGINE=InnoDB DEFAULT CHARSET=latin1;
   
   
ALTER TABLE  DEPARTMENTS ADD CONSTRAINT DEPT_LOC_FK FOREIGN KEY (LOCATION_ID)
	  REFERENCES  LOCATIONS (LOCATION_ID);

	  
-- --------------------------------------------------------
--
-- Table structure for table EMPLOYEES
--
      
   
  CREATE TABLE  EMPLOYEES 
     (	EMPLOYEE_ID INT(6), 
  	FIRST_NAME VARCHAR(20), 
  	LAST_NAME VARCHAR(25) NOT NULL, 
  	EMAIL VARCHAR(25) UNIQUE NOT NULL, 
  	PHONE_NUMBER VARCHAR(20), 
  	HIRE_DATE DATE NOT NULL, 
  	JOB_ID VARCHAR(10) NOT NULL, 
  	SALARY FLOAT(8,2), 
  	COMMISSION_PCT FLOAT(2,2), 
  	MANAGER_ID INT(6), 
  	DEPARTMENT_ID INT(4), 
  	PRIMARY KEY (EMPLOYEE_ID)
   ) ENGINE=InnoDB DEFAULT CHARSET=latin1;
   
   ALTER TABLE  EMPLOYEES ADD CONSTRAINT EMP_DEPT_FK FOREIGN KEY (DEPARTMENT_ID)
   	  REFERENCES  DEPARTMENTS (DEPARTMENT_ID);

   ALTER TABLE  EMPLOYEES ADD CONSTRAINT EMP_JOB_FK FOREIGN KEY (JOB_ID)
   	  REFERENCES  JOBS (JOB_ID);

   ALTER TABLE  EMPLOYEES ADD CONSTRAINT EMP_MANAGER_FK FOREIGN KEY (MANAGER_ID)
	  REFERENCES  EMPLOYEES (EMPLOYEE_ID);
	  
ALTER TABLE  DEPARTMENTS ADD CONSTRAINT DEPT_MGR_FK FOREIGN KEY (MANAGER_ID)
	  REFERENCES  EMPLOYEES (EMPLOYEE_ID);
	  
-- --------------------------------------------------------
--
-- Table structure for table JOB_HISTORY
--	

CREATE TABLE  JOB_HISTORY 
   (	EMPLOYEE_ID INT(6) NOT NULL, 
	START_DATE DATE NOT NULL, 
	END_DATE DATE NOT NULL, 
	JOB_ID VARCHAR(10) NOT NULL, 
	DEPARTMENT_ID INT(4), 
	PRIMARY KEY (EMPLOYEE_ID, START_DATE)
   ) ENGINE=InnoDB DEFAULT CHARSET=latin1;
   
ALTER TABLE  JOB_HISTORY ADD CONSTRAINT JHIST_DEPT_FK FOREIGN KEY (DEPARTMENT_ID)
	  REFERENCES  DEPARTMENTS (DEPARTMENT_ID);

ALTER TABLE  JOB_HISTORY ADD CONSTRAINT JHIST_EMP_FK FOREIGN KEY (EMPLOYEE_ID)
	  REFERENCES  EMPLOYEES (EMPLOYEE_ID);

ALTER TABLE  JOB_HISTORY ADD CONSTRAINT JHIST_JOB_FK FOREIGN KEY (JOB_ID)
	  REFERENCES  JOBS (JOB_ID);  
	  
CREATE TABLE  MESSAGES 
   (       MESSAGE_TEXT VARCHAR(300) 
   ) ENGINE=InnoDB DEFAULT CHARSET=latin1;	  

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `LoadXML`(IN `tablename` VARCHAR(30), IN `path` VARCHAR(255), IN `node` VARCHAR(255), IN `databasename` VARCHAR(255))
    NO SQL
BEGIN
	DECLARE v_finished INTEGER DEFAULT 0;
        DECLARE v_colname varchar(100) DEFAULT "";
	declare xml_content text;   
	declare v_row_index int unsigned default 0;   
	declare v_row_count int unsigned;    
	declare v_xpath_row varchar(255); 
	DECLARE col_value varchar(255);
	declare sqlstring text;
 
	-- declare cursor for colnames
	DEClARE colname_cursor CURSOR FOR 
 	SELECT column_name FROM information_schema.columns WHERE table_name = tablename and table_schema = databasename;
 
	-- declare NOT FOUND handler
	DECLARE CONTINUE HANDLER 
		FOR NOT FOUND SET v_finished = 1;
	set xml_content = load_file(path);
  	SET FOREIGN_KEY_CHECKS=0;
	-- calculate the number of row elements.   
	set v_row_count  = extractValue(xml_content, concat('count(', node, ')')); 
	
	-- loop through all the row elements    
	while v_row_index < v_row_count do
		set @sqlstring=concat('insert into ', tablename, ' values (');
		set v_row_index = v_row_index + 1;        
	        set v_xpath_row = concat(node, '[', v_row_index, ']');

		OPEN colname_cursor;
		set v_finished = 0;
		get_colname: LOOP
			 FETCH colname_cursor INTO v_colname;
			 IF v_finished = 1 THEN 
			 	CLOSE colname_cursor;
				LEAVE get_colname;
			 END IF;
			 -- build colname list
			 set col_value = extractValue(xml_content, concat(v_xpath_row, '/', v_colname));
			 if col_value = "" then
			 	set col_value = NULL;
			 	set @sqlstring = concat(@sqlstring, 'NULL,');
			 else
			 	set @sqlstring = concat(@sqlstring, '"', col_value, '",');
			 end if;		
			insert into messages select concat('col is ', v_colname, ': col value is ', col_value);  		
		END LOOP get_colname;
		set @sqlstring = concat(left(@sqlstring,length(@sqlstring)-1), ')');
		insert into messages select concat('sqlstring is ', @sqlstring);
		PREPARE stmt FROM @sqlstring;
		EXECUTE stmt;
		DEALLOCATE PREPARE stmt;
		COMMIT;	
	end while;
	     SET FOREIGN_KEY_CHECKS=1;
END$$
DELIMITER ;