INSERT INTO CARD_YEAR (CARD_YEAR) VALUES (1983), (1984), (1985),
  (1986), (1987), (1988), (1989), (1990), (1991), (1992), (1993),
  (1994), (1995), (1996), (1997), (1998), (1999), (2000), (2001),
  (2002), (2003), (2004), (2005), (2006), (2007), (2008), (2009),
  (2010), (2011), (2012), (2013), (2014), (2015), (2016), (2017);

INSERT INTO PLAYER (FIRST_NAME, LAST_NAME, DOB) VALUES ('Derek', 'Jeter', '1974-06-26'),
  ('Ichiro', 'Suzuki', '1973-10-22'), ('Ryan', 'Zimmerman', '1984-09-28'), ('Alex', 'Rodriguez', '1975-07-27');

INSERT INTO PLAYER (FIRST_NAME, LAST_NAME, SUFFIX, DOB) VALUES ('Ken', 'Griffey', 'Jr.', '1969-11-21'),
  ('Ken', 'Griffey', 'Sr.', '1950-04-10');

INSERT INTO BRAND (BRAND_NAME) VALUES ('Topps'),
                                      ('Upper Deck'),
                                      ('Fleer'),
                                      ('Bowman'),
                                      ('TriStar');

INSERT INTO CARD_SET (YEAR_ID, BRAND_ID) VALUES (24, 1), (25, 1), (26, 1);
INSERT INTO CARD_SET (YEAR_ID, BRAND_ID, SET_NAME) VALUES (25, 2, 'Masterpieces'), (26, 2, 'Masterpieces'),
  (35, 1, 'Allen & Ginter');


INSERT INTO TEAM (CITY, TEAM_NAME) VALUES ('New York', 'Yankees'), ('Cincinnati', 'Reds'),
  ('Seattle', 'Mariners'), ('Washington', 'Nationals'), ('Boston', 'Red Sox'), ('Baltimore', 'Orioles'),
  ('Tampa Bay', 'Rays'), ('Toronto', 'Blue Jays'), ('Chicago', 'White Sox'), ('Cleveland', 'Indians'),
  ('Kansas City', 'Royals'), ('Minnesota', 'Twins'), ('Detroit', 'Tigers'), ('Los Angeles', 'Angels'),
  ('Houston', 'Astros'), ('Texas', 'Rangers'), ('Oakland', 'Athletics'), ('New York', 'Mets'),
  ('Philadelphia', 'Phillies'), ('Miami', 'Marlins'), ('Atlanta', 'Braves'), ('Chicago', 'Cubs'),
  ('Pittsburgh', 'Pirates'), ('Milwaukee', 'Brewers'), ('St. Louis', 'Cardinals'), ('Los Angeles', 'Dodgers'),
  ('San Francisco', 'Giants'), ('San Diego', 'Padres'), ('Arizona', 'Diamondbacks'), ('Colorado', 'Rockies'), ('Not a player', 'Not a player');

INSERT INTO CARD (SET_ID, PLAYER_ID, TEAM_ID, CARD_NUM, IMAGE_NAME) VALUES (4, 1, 1, '30', '2007_ud_masterpieces_derek_jeter.jpg');
INSERT INTO CARD (SET_ID, PLAYER_ID, TEAM_ID, CARD_NUM, IMAGE_NAME) VALUES (1, 5, 2, '387', '2006_topps_ken_griffey_jr.jpg');

