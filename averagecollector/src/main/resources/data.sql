INSERT INTO CARD_YEAR (CARD_YEAR) VALUES (1983), (1984), (1985),
  (1986), (1987), (1988), (1989), (1990), (1991), (1992), (1993),
  (1994), (1995), (1996), (1997), (1998), (1999), (2000), (2001),
  (2002), (2003), (2004), (2005), (2006), (2007), (2008), (2009),
  (2010), (2011), (2012);

INSERT INTO PLAYER (FIRST_NAME, LAST_NAME, DOB) VALUES ('Derek', 'Jeter', '1974-06-26'),
                                                       ('Ichiro', 'Suzuki', DATE '1973-10-22'),
                                                       ('Ryan', 'Zimmerman', DATE '1984-09-28'),
                                                       ('Alex', 'Rodriguez', DATE '1975-07-27');

INSERT INTO PLAYER (FIRST_NAME, LAST_NAME, SUFFIX, DOB) VALUES ('Ken', 'Griffey', 'Jr.', DATE '1969-11-21');

INSERT INTO BRAND (BRAND_NAME) VALUES ('Topps'),
                                      ('Upper Deck'),
                                      ('Fleer'),
                                      ('Bowman'),
                                      ('TriStar');

INSERT INTO CARD_SET (BRAND_ID) VALUES (1);
INSERT INTO CARD_SET (BRAND_ID, SET_NAME) VALUES (2, 'Masterpieces');

INSERT INTO TEAM (CITY, TEAM_NAME) VALUES ('New York', 'Yankees'), ('Cincinnati', 'Reds'),
  ('Seattle', 'Mariners'), ('Washington', 'Nationals'), ('Boston', 'Red Sox'), ('Baltimore', 'Orioles'),
  ('Tampa Bay', 'Rays'), ('Toronto', 'Blue Jays'), ('Chicago', 'White Sox'), ('Cleveland', 'Indians'),
  ('Kansas City', 'Royals'), ('Minnesota', 'Twins'), ('Detroit', 'Tigers'), ('Los Angeles', 'Angels'),
  ('Houston', 'Astros'), ('Texas', 'Rangers'), ('Oakland', 'Athletics'), ('New York', 'Mets'),
  ('Philadelphia', 'Phillies'), ('Miami', 'Marlins'), ('Atlanta', 'Braves'), ('Chicago', 'Cubs'),
  ('Pittsburgh', 'Pirates'), ('Milwaukee', 'Brewers'), ('St. Louis', 'Cardinals'), ('Los Angeles', 'Dodgers'),
  ('San Francisco', 'Giants'), ('San Diego', 'Padres'), ('Arizona', 'Diamondbacks'), ('Colorado', 'Rockies');

INSERT INTO CARD (YEAR_ID, SET_ID, CARD_NUM, IMAGE_NAME) VALUES ((SELECT ID FROM CARD_YEAR WHERE CARD_YEAR = 2006), 1, '387', '2006_topps_ken_griffey_jr.jpg');
INSERT INTO PLAYER_TO_CARD (PLAYER_ID, CARD_ID) VALUES (2, 1);
INSERT INTO TEAM_TO_CARD (TEAM_ID, CARD_ID) VALUES (2, 1);

INSERT INTO CARD (YEAR_ID, SET_ID, CARD_NUM, IMAGE_NAME) VALUES ((SELECT ID FROM CARD_YEAR WHERE CARD_YEAR = 2007), 2, '30', '2007_ud_masterpieces_derek_jeter.jpg');
INSERT INTO PLAYER_TO_CARD (PLAYER_ID, CARD_ID) VALUES (1, 2);
INSERT INTO TEAM_TO_CARD (TEAM_ID, CARD_ID) VALUES (1, 2);
