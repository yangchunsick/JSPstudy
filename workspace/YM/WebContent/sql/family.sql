DROP TABLE family;
CREATE TABLE family
(
 num		NUMBER PRIMARY KEY, 	 -- 번호
 name		VARCHAR2(20),			 -- 이름
 depart		VARCHAR2(20),			 -- 부서
 birthday	VARCHAR2(10) 			 -- 생일 '2001-01-22'
);

DROP SEQUENCE family_seq;
CREATE SEQUENCE family_seq INCREMENT BY 1 START WITH 1 NOCYCLE NOCACHE;