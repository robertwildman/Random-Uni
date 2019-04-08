--
-- Database: albums
--
CREATE DATABASE IF NOT EXISTS albums DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE albums;

DROP TABLE IF EXISTS PLAYED;
DROP TABLE IF EXISTS SONGS;
DROP TABLE IF EXISTS ALBUMS;
DROP TABLE IF EXISTS MESSAGES;
DROP PROCEDURE IF EXISTS LoadXML;

-- --------------------------------------------------------
--
-- Table structure for table ALBUMS
--

CREATE TABLE  ALBUMS
   (    ALBUM_ID INT NOT NULL, 
    ALBUM_TITLE VARCHAR(50) NOT NULL, 
    ARTIST VARCHAR(50), 
     CONSTRAINT ALBUMS_PK PRIMARY KEY (ALBUM_ID)
   ) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------
--
-- Table structure for table SONGS
--

CREATE TABLE  SONGS 
   (    SONG_ID INT NOT NULL, 
    SONG_TITLE VARCHAR(50) NOT NULL, 
    ARTIST VARCHAR(50), 
    ALBUM_ID INT, 
     CONSTRAINT SONGS_PK PRIMARY KEY (SONG_ID) 
   ) ENGINE=InnoDB DEFAULT CHARSET=latin1;
   
-- --------------------------------------------------------
--
-- Table structure for table PLAYED
--

CREATE TABLE  PLAYED 
   (    PLAYED_ID INT NOT NULL, 
    SONG_ID INT NOT NULL, 
    PLAYED_DATE DATE NOT NULL, 
     CONSTRAINT PLAYED_PK PRIMARY KEY (PLAYED_ID)
   ) ENGINE=InnoDB DEFAULT CHARSET=latin1;
   
ALTER TABLE  PLAYED ADD CONSTRAINT PLAYED_FK FOREIGN KEY (SONG_ID)
      REFERENCES  SONGS (SONG_ID);

ALTER TABLE  SONGS ADD CONSTRAINT SONGS_FK FOREIGN KEY (ALBUM_ID)
      REFERENCES  ALBUMS (ALBUM_ID);
      
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