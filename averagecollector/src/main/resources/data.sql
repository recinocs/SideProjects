INSERT INTO CARD_YEAR (CARD_YEAR) VALUES (1983), (1984), (1985),
  (1986), (1987), (1988), (1989), (1990), (1991), (1992), (1993),
  (1994), (1995), (1996), (1997), (1998), (1999), (2000), (2001),
  (2002), (2003), (2004), (2005), (2006), (2007), (2008), (2009),
  (2010), (2011), (2012), (2013), (2014), (2015), (2016), (2017);

INSERT INTO TEAM (CITY, TEAM_NAME) VALUES ('New York', 'Yankees'), ('Cincinnati', 'Reds'),
  ('Seattle', 'Mariners'), ('Washington', 'Nationals'), ('Boston', 'Red Sox'), ('Baltimore', 'Orioles'),
  ('Tampa Bay', 'Rays'), ('Toronto', 'Blue Jays'), ('Chicago', 'White Sox'), ('Cleveland', 'Indians'),
  ('Kansas City', 'Royals'), ('Minnesota', 'Twins'), ('Detroit', 'Tigers'), ('Los Angeles', 'Angels'),
  ('Houston', 'Astros'), ('Texas', 'Rangers'), ('Oakland', 'Athletics'), ('New York', 'Mets'),
  ('Philadelphia', 'Phillies'), ('Miami', 'Marlins'), ('Atlanta', 'Braves'), ('Chicago', 'Cubs'),
  ('Pittsburgh', 'Pirates'), ('Milwaukee', 'Brewers'), ('St. Louis', 'Cardinals'), ('Los Angeles', 'Dodgers'),
  ('San Francisco', 'Giants'), ('San Diego', 'Padres'), ('Arizona', 'Diamondbacks'), ('Colorado', 'Rockies'),
  ('Not a player', 'Not a player'), ('Japan', 'National Team');

INSERT INTO BRAND (BRAND_NAME) VALUES ('Topps'), ('Upper Deck'), ('Fleer'), ('Bowman'), ('TriStar');

INSERT INTO PLAYER (FIRST_NAME, LAST_NAME, DOB) VALUES ('Derek', 'Jeter', '1974-06-26'),
                                                       ('Ichiro', 'Suzuki', '1973-10-22'),
                                                       ('Ryan', 'Zimmerman', '1984-09-28'),
                                                       ('Alex', 'Rodriguez', '1975-07-27'),
                                                       ('Giancarlo', 'Stanton', '1989-11-08'),
                                                       ('George', 'Springer', '1989-09-19'),
                                                       ('Yoshimoto', 'Tsutsugoh', '1991-11-26');
INSERT INTO PLAYER (FIRST_NAME, LAST_NAME, SUFFIX, DOB) VALUES ('Ken', 'Griffey', 'Jr.', '1969-11-21'),
                                                               ('Ken', 'Griffey', 'Sr.', '1950-04-10');

INSERT INTO CARD_SET (YEAR_ID, BRAND_ID) VALUES (24, 1);
INSERT INTO CARD_SET (YEAR_ID, BRAND_ID) VALUES (25, 1);
INSERT INTO CARD_SET (YEAR_ID, BRAND_ID) VALUES (26, 1);
INSERT INTO CARD_SET (YEAR_ID, BRAND_ID, SET_NAME) VALUES (25, 2, 'Masterpieces');
INSERT INTO CARD_SET (YEAR_ID, BRAND_ID, SET_NAME) VALUES (26, 2, 'Masterpieces');
INSERT INTO CARD_SET (YEAR_ID, BRAND_ID, SET_NAME) VALUES (35, 1, 'Allen & Ginter');

INSERT INTO CARD (SET_ID, PLAYER_ID, TEAM_ID, CARD_NUM, IMAGE_FRONT) VALUES (4, (SELECT id FROM PLAYER WHERE FIRST_NAME = 'Derek' AND LAST_NAME = 'Jeter'),
                                                                                1, '30', '2007_ud_masterpieces_derek_jeter.jpg'),
                                                                            (1, (SELECT id FROM PLAYER WHERE FIRST_NAME = 'Ken' AND LAST_NAME = 'Griffey' AND SUFFIX = 'Jr.'),
                                                                                2, '387', '2006_topps_ken_griffey_jr.jpg');

INSERT INTO CARD (SET_ID, PLAYER_ID, TEAM_ID, CARD_NUM, INSERT_TYPE, MEM_TYPE) VALUES (6, (SELECT id FROM PLAYER WHERE FIRST_NAME = 'Giancarlo' AND LAST_NAME = 'Stanton'),
                                                                                          20, 'GS', 'Full-Size Relic Design A', 'Jersey Card'),
                                                                                      (6, (SELECT id FROM PLAYER WHERE FIRST_NAME = 'George' AND LAST_NAME = 'Springer'),
                                                                                          15, 'GSP', 'Full-Size Relic Design B', 'Jersey Card');

INSERT INTO CARD (SET_ID, PLAYER_ID, TEAM_ID, CARD_NUM, INSERT_TYPE, SERIAL_NUM, MEM_TYPE) VALUES (6, (SELECT id FROM PLAYER WHERE FIRST_NAME = 'Yoshimoto' AND LAST_NAME = 'Tsutsugoh'),
                                                                                                      32, 'YT', 'World Baseball Classic Relic', '92/99', 'Jersey Card');

