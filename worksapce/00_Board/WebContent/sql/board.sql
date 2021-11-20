DROP TABLE board;
CREATE TABLE board
(
	idx			NUMBER,
	title		VARCHAR2(20),
	writer		VARCHAR2(20),
	content		VARCHAR2(4000),
	register	VARCHAR2(10)
	
	PRIMARY KEY(idx)
);