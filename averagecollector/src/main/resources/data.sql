INSERT INTO CARD_YEAR (CARD_YEAR) VALUES  (1982), (1983), (1984),
  (1985), (1986), (1987), (1988), (1989), (1990), (1991), (1992),
  (1993), (1994), (1995), (1996), (1997), (1998), (1999), (2000),
  (2001), (2002), (2003), (2004), (2005), (2006), (2007), (2008),
  (2009), (2010), (2011), (2012), (2013), (2014), (2015), (2016),
  (2017);

INSERT INTO TEAM (CITY, TEAM_NAME) VALUES ('New York', 'Yankees'), ('Cincinnati', 'Reds'),
  ('Seattle', 'Mariners'), ('Washington', 'Nationals'), ('Boston', 'Red Sox'), ('Baltimore', 'Orioles'),
  ('Tampa Bay', 'Rays'), ('Toronto', 'Blue Jays'), ('Chicago', 'White Sox'), ('Cleveland', 'Indians'),
  ('Kansas City', 'Royals'), ('Minnesota', 'Twins'), ('Detroit', 'Tigers'), ('Los Angeles', 'Angels'),
  ('Houston', 'Astros'), ('Texas', 'Rangers'), ('Oakland', 'Athletics'), ('New York', 'Mets'),
  ('Philadelphia', 'Phillies'), ('Miami', 'Marlins'), ('Atlanta', 'Braves'), ('Chicago', 'Cubs'),
  ('Pittsburgh', 'Pirates'), ('Milwaukee', 'Brewers'), ('St. Louis', 'Cardinals'), ('Los Angeles', 'Dodgers'),
  ('San Francisco', 'Giants'), ('San Diego', 'Padres'), ('Arizona', 'Diamondbacks'), ('Colorado', 'Rockies'),
  ('Not a player', 'Not a player'), ('Japan', 'National Team');

INSERT INTO BRAND (BRAND_NAME) VALUES ('Topps'), ('Upper Deck'), ('Fleer'), ('Bowman'), ('TriStar'),
('Donruss');

INSERT INTO PLAYER (FIRST_NAME, LAST_NAME, DOB) VALUES ('Derek', 'Jeter', '1974-06-26'),
('Ichiro', 'Suzuki', '1973-10-22'), ('Ryan', 'Zimmerman', '1984-09-28'), ('Alex', 'Rodriguez', '1975-07-27'),
('Giancarlo', 'Stanton', '1989-11-08'), ('George', 'Springer', '1989-09-19'), ('Yoshimoto', 'Tsutsugoh', '1991-11-26'),
('Steve', 'Stone', '1947-07-14'), ('Bo', 'Jackson', '1962-11-30'), ('Rickey', 'Henderson', '1958-12-25'),
('Ozzie', 'Smith', '1954-12-26'), ('Dale', 'Murphy', '1956-03-12'), ('Ryan', 'Rupe', '1975-03-31'),
('Magglio', 'Ordonez', '1974-01-28'), ('Todd', 'Walker', '1973-05-25'), ('Randy', 'Johnson', '1963-09-10'),
('Luis', 'Gonzalez', '1967-09-03');

INSERT INTO PLAYER (FIRST_NAME, LAST_NAME, SUFFIX, DOB) VALUES ('Ken', 'Griffey', 'Jr.', '1969-11-21'),
                                                               ('Ken', 'Griffey', 'Sr.', '1950-04-10');

INSERT INTO CARD_SET (YEAR_ID, BRAND_ID) VALUES ((SELECT id FROM CARD_YEAR WHERE CARD_YEAR = 1982), 6),
((SELECT id FROM CARD_YEAR WHERE CARD_YEAR = 1986), 1), ((SELECT id FROM CARD_YEAR WHERE CARD_YEAR = 1990), 4),
((SELECT id FROM CARD_YEAR WHERE CARD_YEAR = 1993), 2), ((SELECT id FROM CARD_YEAR WHERE CARD_YEAR = 2006), 1),
((SELECT id FROM CARD_YEAR WHERE CARD_YEAR = 2007), 1), ((SELECT id FROM CARD_YEAR WHERE CARD_YEAR = 2008), 1);

INSERT INTO CARD_SET (YEAR_ID, BRAND_ID, SET_NAME) VALUES ((SELECT id FROM CARD_YEAR WHERE CARD_YEAR = 1992), 3, 'Ultra'),
((SELECT id FROM CARD_YEAR WHERE CARD_YEAR = 2000), 3, 'Ultra'), ((SELECT id FROM CARD_YEAR WHERE CARD_YEAR = 2001), 3, 'Legacy'),
((SELECT id FROM CARD_YEAR WHERE CARD_YEAR = 2001), 3, 'Platinum'), ((SELECT id FROM CARD_YEAR WHERE CARD_YEAR = 2001), 2, 'Gold Glove'),
((SELECT id FROM CARD_YEAR WHERE CARD_YEAR = 2007), 2, 'Masterpieces'), ((SELECT id FROM CARD_YEAR WHERE CARD_YEAR = 2008), 2, 'Masterpieces'),
((SELECT id FROM CARD_YEAR WHERE CARD_YEAR = 2017), 1, 'Allen & Ginter');

INSERT INTO CARD (SET_ID, PLAYER_ID, TEAM_ID, CARD_NUM, IMAGE_FRONT) VALUES (13, (SELECT id FROM PLAYER WHERE FIRST_NAME = 'Derek' AND LAST_NAME = 'Jeter'),
                                                                                (SELECT id FROM TEAM WHERE TEAM_NAME = 'Yankees'), '30', '2007_ud_masterpieces_derek_jeter.jpg'),
                                                                            (5, (SELECT id FROM PLAYER WHERE FIRST_NAME = 'Ken' AND LAST_NAME = 'Griffey' AND SUFFIX = 'Jr.'),
                                                                                (SELECT id FROM TEAM WHERE TEAM_NAME = 'Reds'), '387', '2006_topps_ken_griffey_jr.jpg');

INSERT INTO CARD (SET_ID, PLAYER_ID, TEAM_ID, CARD_NUM) VALUES (2, (SELECT id FROM PLAYER WHERE FIRST_NAME = 'Bo' AND LAST_NAME = 'Jackson'),
                                                                   (SELECT id FROM TEAM WHERE TEAM_NAME = 'Royals'), '50T'),
                                                               (3, (SELECT id FROM PLAYER WHERE FIRST_NAME = 'Rickey' AND LAST_NAME = 'Henderson'),
                                                                   (SELECT id FROM TEAM WHERE CITY = 'Oakland'), '457'),
                                                               (8, (SELECT id FROM PLAYER WHERE FIRST_NAME = 'Ozzie' AND LAST_NAME = 'Smith'),
                                                                   (SELECT id FROM TEAM WHERE TEAM_NAME = 'Cardinals'), '271'),
                                                               (4, (SELECT id FROM PLAYER WHERE FIRST_NAME = 'Dale' AND LAST_NAME = 'Murphy'),
                                                                   (SELECT id FROM TEAM WHERE TEAM_NAME = 'Phillies'), '32');


INSERT INTO CARD (SET_ID, PLAYER_ID, TEAM_ID, CARD_NUM, MEM_TYPE) VALUES (1, (SELECT id FROM PLAYER WHERE FIRST_NAME = 'Steve' AND LAST_NAME = 'Stone'),
                                                                          (SELECT id FROM TEAM WHERE TEAM_NAME = 'Orioles'), '357', 'Autograph');

INSERT INTO CARD (SET_ID, PLAYER_ID, TEAM_ID, CARD_NUM, INSERT_TYPE, MEM_TYPE) VALUES (15, (SELECT id FROM PLAYER WHERE FIRST_NAME = 'Giancarlo' AND LAST_NAME = 'Stanton'),
                                                                                          (SELECT id FROM TEAM WHERE TEAM_NAME = 'Marlins'), 'GS', 'Full-Size Relic Design A', 'Jersey Card'),
                                                                                      (15, (SELECT id FROM PLAYER WHERE FIRST_NAME = 'George' AND LAST_NAME = 'Springer'),
                                                                                          (SELECT id FROM TEAM WHERE TEAM_NAME = 'Astros'), 'GSP', 'Full-Size Relic Design B', 'Jersey Card'),
                                                                                      (10, (SELECT id FROM PLAYER WHERE FIRST_NAME = 'Magglio'),
                                                                                          (SELECT id FROM TEAM WHERE TEAM_NAME = 'White Sox'), '23', 'Hit Kings', 'Bat'),
                                                                                      (11, (SELECT id FROM PLAYER WHERE FIRST_NAME = 'Todd' AND LAST_NAME = 'Walker'),
                                                                                          (SELECT id FROM TEAM WHERE TEAM_NAME = 'Rockies'), 'TOWA', 'National Patch Time', 'Jersey'),
                                                                                      (12, (SELECT id FROM PLAYER WHERE FIRST_NAME = 'Randy' AND LAST_NAME = 'Johnson'),
                                                                                          (SELECT id FROM TEAM WHERE CITY = 'Arizona'), 'JG', 'Official Issue Game Ball', 'Ball'),
                                                                                      (12, (SELECT id FROM PLAYER WHERE FIRST_NAME = 'Luis' AND LAST_NAME = 'Gonzalez'),
                                                                                          (SELECT id FROM TEAM WHERE CITY = 'Arizona'), 'JG', 'Official Issue Game Ball', 'Ball');

INSERT INTO CARD (SET_ID, PLAYER_ID, TEAM_ID, CARD_NUM, INSERT_TYPE, SERIAL_NUM, MEM_TYPE) VALUES (15, (SELECT id FROM PLAYER WHERE FIRST_NAME = 'Yoshimoto' AND LAST_NAME = 'Tsutsugoh'),
                                                                                                      (SELECT id FROM TEAM WHERE CITY = 'Japan'), 'YT', 'World Baseball Classic Relic', '92/99', 'Jersey Card'),
                                                                                                  (9, (SELECT id FROM PLAYER WHERE FIRST_NAME = 'Ryan' AND LAST_NAME = 'Rupe'),
                                                                                                      (SELECT id FROM TEAM WHERE TEAM_NAME = 'Rays'), '51', 'Fresh Ink', '613/1000', 'Autograph');

