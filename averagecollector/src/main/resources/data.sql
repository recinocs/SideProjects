INSERT INTO YEAR (YEAR) VALUES (1983); //1
INSERT INTO YEAR (YEAR) VALUES (1984); //2
INSERT INTO YEAR (YEAR) VALUES (1985); //3
INSERT INTO YEAR (YEAR) VALUES (1986); //4
INSERT INTO YEAR (YEAR) VALUES (1987); //5
INSERT INTO YEAR (YEAR) VALUES (1988); //6
INSERT INTO YEAR (YEAR) VALUES (1989); //7
INSERT INTO YEAR (YEAR) VALUES (1990); //8
INSERT INTO YEAR (YEAR) VALUES (1991); //9
INSERT INTO YEAR (YEAR) VALUES (1992); //10
INSERT INTO YEAR (YEAR) VALUES (1993); //11
INSERT INTO YEAR (YEAR) VALUES (1994); //12
INSERT INTO YEAR (YEAR) VALUES (1995); //13
INSERT INTO YEAR (YEAR) VALUES (1996); //14
INSERT INTO YEAR (YEAR) VALUES (1997); //15
INSERT INTO YEAR (YEAR) VALUES (1998); //16
INSERT INTO YEAR (YEAR) VALUES (1999); //17
INSERT INTO YEAR (YEAR) VALUES (2000); //18
INSERT INTO YEAR (YEAR) VALUES (2001); //19
INSERT INTO YEAR (YEAR) VALUES (2002); //20
INSERT INTO YEAR (YEAR) VALUES (2003); //21
INSERT INTO YEAR (YEAR) VALUES (2004); //22
INSERT INTO YEAR (YEAR) VALUES (2005); //23
INSERT INTO YEAR (YEAR) VALUES (2006); //24
INSERT INTO YEAR (YEAR) VALUES (2007); //25
INSERT INTO YEAR (YEAR) VALUES (2008); //26
INSERT INTO YEAR (YEAR) VALUES (2009); //27
INSERT INTO YEAR (YEAR) VALUES (2010); //28
INSERT INTO YEAR (YEAR) VALUES (2011); //29
INSERT INTO YEAR (YEAR) VALUES (2012); //30

INSERT INTO PLAYER (FIRST_NAME, LAST_NAME, DOB) VALUES ('Derek', 'Jeter', '1974-06-26');
INSERT INTO PLAYER (FIRST_NAME, LAST_NAME, SUFFIX, DOB) VALUES ('Ken', 'Griffey', 'Jr.', DATE '1969-11-21');
INSERT INTO PLAYER (FIRST_NAME, LAST_NAME, DOB) VALUES ('Ichiro', 'Suzuki', DATE '1973-10-22');
INSERT INTO PLAYER (FIRST_NAME, LAST_NAME, DOB) VALUES ('Ryan', 'Zimmerman', DATE '1984-09-28');
INSERT INTO PLAYER (FIRST_NAME, LAST_NAME, DOB) VALUES ('Alex', 'Rodriguez', DATE '1975-07-27');

INSERT INTO BRAND (BRAND_NAME) VALUES ('Topps');
INSERT INTO BRAND (BRAND_NAME) VALUES ('Upper Deck');
INSERT INTO BRAND (BRAND_NAME) VALUES ('Fleer');
INSERT INTO BRAND (BRAND_NAME) VALUES ('Bowman');
INSERT INTO BRAND (BRAND_NAME) VALUES ('TriStar');

INSERT INTO CARD_SET (BRAND_ID) VALUES (1);
INSERT INTO CARD_SET (BRAND_ID, SET_NAME) VALUES (2, 'Masterpieces');

INSERT INTO TEAM (CITY, TEAM_NAME) VALUES ('New York', 'Yankees');
INSERT INTO TEAM (CITY, TEAM_NAME) VALUES ('Cincinnati', 'Reds');
INSERT INTO TEAM (CITY, TEAM_NAME) VALUES ('Seattle', 'Mariners');
INSERT INTO TEAM (CITY, TEAM_NAME) VALUES ('Washington', 'Nationals');

INSERT INTO CARD (YEAR_ID, SET_ID, CARD_NUM, IMAGE_NAME) VALUES (24, 1, '387', '2006_topps_ken_griffey_jr.jpg');
INSERT INTO CARD (YEAR_ID, SET_ID, CARD_NUM, IMAGE_NAME) VALUES (25, 2, '30', '2007_ud_masterpieces_derek_jeter.jpg');

INSERT INTO PLAYER_TO_CARD (PLAYER_ID, CARD_ID) VALUES (2, 1);
INSERT INTO PLAYER_TO_CARD (PLAYER_ID, CARD_ID) VALUES (1, 2);

INSERT INTO TEAM_TO_CARD (TEAM_ID, CARD_ID) VALUES (2, 1);
INSERT INTO TEAM_TO_CARD (TEAM_ID, CARD_ID) VALUES (1, 2);