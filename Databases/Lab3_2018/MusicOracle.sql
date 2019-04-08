declare
  drop_sql long;
begin
  for rec in (select table_name 
              from   user_tables 
              where  table_name in ('ALBUMS','SONGS', 'PLAYED')
             )
  loop
  begin
    drop_sql := 'drop table '||rec.table_name||'  CASCADE CONSTRAINTS purge';
    dbms_output.put_line(rec.table_name); 
    execute immediate drop_sql;
    EXCEPTION
      WHEN OTHERS THEN
           IF SQLCODE = -942 THEN
              DBMS_OUTPUT.PUT_LINE('Table Cannot be dropped');
              NULL;
           END IF;    
  end;
  end loop;          
END;
/

CREATE TABLE  "ALBUMS" 
   (    "ALBUM_ID" NUMBER NOT NULL ENABLE, 
    "ALBUM_TITLE" VARCHAR2(50) NOT NULL ENABLE, 
    "ARTIST" VARCHAR2(50), 
     CONSTRAINT "ALBUMS_PK" PRIMARY KEY ("ALBUM_ID") ENABLE
   )
/
CREATE TABLE  "SONGS" 
   (    "SONG_ID" NUMBER NOT NULL ENABLE, 
    "SONG_TITLE" VARCHAR2(50) NOT NULL ENABLE, 
    "ARTIST" VARCHAR2(50), 
    "ALBUM_ID" NUMBER, 
     CONSTRAINT "SONGS_PK" PRIMARY KEY ("SONG_ID") ENABLE
   )
/
CREATE TABLE  "PLAYED" 
   (    "PLAYED_ID" NUMBER NOT NULL ENABLE, 
    "SONG_ID" NUMBER NOT NULL ENABLE, 
    "PLAYED_DATE" DATE NOT NULL ENABLE, 
     CONSTRAINT "PLAYED_PK" PRIMARY KEY ("PLAYED_ID") ENABLE
   )
/
ALTER TABLE  "PLAYED" ADD CONSTRAINT "PLAYED_FK" FOREIGN KEY ("SONG_ID")
      REFERENCES  "SONGS" ("SONG_ID") ENABLE
/
ALTER TABLE  "SONGS" ADD CONSTRAINT "SONGS_FK" FOREIGN KEY ("ALBUM_ID")
      REFERENCES  "ALBUMS" ("ALBUM_ID") ENABLE
/
